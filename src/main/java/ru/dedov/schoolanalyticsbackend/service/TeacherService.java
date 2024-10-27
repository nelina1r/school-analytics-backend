package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dedov.schoolanalyticsbackend.dto.AverageSubjectGradeDto;
import ru.dedov.schoolanalyticsbackend.dto.SignUpRequest;
import ru.dedov.schoolanalyticsbackend.dto.StudentMetricsDto;
import ru.dedov.schoolanalyticsbackend.dto.TeacherDashboardDto;
import ru.dedov.schoolanalyticsbackend.exception.UserAlreadyExistsException;
import ru.dedov.schoolanalyticsbackend.model.entity.*;
import ru.dedov.schoolanalyticsbackend.model.entity.Class;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.AttendanceStatus;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.Role;
import ru.dedov.schoolanalyticsbackend.model.repository.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Сервис для учителей
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Service
@RequiredArgsConstructor
public class TeacherService {
	private final TeacherRepository teacherRepository;
	private final UserService userService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ClassRepository classRepository;
	private final StudentRepository studentRepository;
	private final AttendanceRepository attendanceRepository;
	private final GradeRepository gradeRepository;

	public TeacherDashboardDto getClassDashboard(Long classId) {
		Class schoolClass = classRepository.findById(classId)
			.orElseThrow(() -> new NoSuchElementException("Класс не найден"));

		List<Student> students = studentRepository.findByClass(schoolClass);

		List<StudentMetricsDto> studentMetrics = students.stream().map(student -> {
			long totalClasses = attendanceRepository.countByStudent(student);
			long attendedClasses = attendanceRepository.countByStudentAndStatus(student, AttendanceStatus.PRESENT);
			double attendancePercent = totalClasses > 0 ? (double) attendedClasses / totalClasses * 100 : 0;
			List<Grade> grades = gradeRepository.findByStudent(student);
			double avgGrade = grades.stream().mapToInt(Grade::getValue).average().orElse(0.0);
			List<AverageSubjectGradeDto> avgSubjectGrades = grades.stream()
				.collect(Collectors.groupingBy(grade -> grade.getSubject().getName()))
				.entrySet().stream()
				.map(entry -> new AverageSubjectGradeDto(
					entry.getKey(),
					entry.getValue().stream().mapToInt(Grade::getValue).average().orElse(0.0)))
				.collect(Collectors.toList());

			return new StudentMetricsDto(student.getFio(), attendancePercent, avgGrade, avgSubjectGrades);
		}).collect(Collectors.toList());
		double classAverageAttendance = studentMetrics.stream()
			.mapToDouble(StudentMetricsDto::getAttendancePercent)
			.average().orElse(0.0);
		double classAverageGrade = studentMetrics.stream()
			.mapToDouble(StudentMetricsDto::getAverageGrade)
			.average().orElse(0.0);
		return new TeacherDashboardDto(classAverageAttendance, classAverageGrade, studentMetrics);
	}

	public List<Class> listMyClasses() {
		User user = userService.getCurrentUser();
		Teacher currentTeacher = teacherRepository.findById(user.getId()).orElseThrow(NoSuchElementException::new);
		return currentTeacher.getAssignedClasses();
	}

	/**
	 * Создать нового пользователя с ролью учителя
	 */
	public void createNewTeacher(SignUpRequest request) {
		Teacher teacher = new Teacher();
		teacher.setFio(request.getFio());
		teacher.setUsername(request.getUsername());
		teacher.setEmail(request.getEmail());
		teacher.setPassword(passwordEncoder.encode(request.getPassword()));
		teacher.setRole(Role.ROLE_TEACHER);
		if (userRepository.existsByUsername(teacher.getUsername())) {
			throw new UserAlreadyExistsException("Учитель с таким именем уже существует");
		}
		if (userRepository.existsByEmail(teacher.getEmail())) {
			throw new UserAlreadyExistsException("Учитель с таким email уже существует");
		}
		teacherRepository.save(teacher);
	}
}
