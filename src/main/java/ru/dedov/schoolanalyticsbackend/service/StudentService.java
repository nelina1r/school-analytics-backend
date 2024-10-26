package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dedov.schoolanalyticsbackend.dto.AverageSubjectGradeDto;
import ru.dedov.schoolanalyticsbackend.dto.StudentDashboardDto;
import ru.dedov.schoolanalyticsbackend.model.entity.Grade;
import ru.dedov.schoolanalyticsbackend.model.entity.Student;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.AttendanceStatus;
import ru.dedov.schoolanalyticsbackend.model.repository.AttendanceRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.GradeRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Сервис для учеников
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;
	private final GradeRepository gradeRepository;
	private final AttendanceRepository attendanceRepository;
	private final UserService userService;

	public StudentDashboardDto getMyDashboard() {
		Student student = studentRepository.findById(userService.getCurrentUser().getId())
			.orElseThrow(() -> new NoSuchElementException("Студент не найден"));

		// Общий процент посещаемости
		long totalClasses = attendanceRepository.countByStudent(student);
		long attendedClasses = attendanceRepository.countByStudentAndStatus(student, AttendanceStatus.PRESENT);
		double generalAttendancePercentile = totalClasses > 0 ? (double) attendedClasses / totalClasses * 100 : 0;

		// Общий средний балл
		List<Grade> grades = gradeRepository.findByStudent(student);
		double generalAverageGradeValue = grades.stream()
			.mapToInt(Grade::getValue)
			.average()
			.orElse(0.0);

		// Средний балл по каждому предмету
		List<AverageSubjectGradeDto> averageSubjectGrades = grades.stream()
			.collect(Collectors.groupingBy(grade -> grade.getSubject().getName()))
			.entrySet().stream()
			.map(entry -> new AverageSubjectGradeDto(
				entry.getKey(),
				entry.getValue().stream().mapToInt(Grade::getValue).average().orElse(0.0)))
			.collect(Collectors.toList());

		// Создание и возврат DTO дашборда
		StudentDashboardDto dashboardDto = new StudentDashboardDto();
		dashboardDto.setGeneralAttendancePercentile(generalAttendancePercentile);
		dashboardDto.setGeneralAverageGradeValue(generalAverageGradeValue);
		dashboardDto.setAverageSubjectGrades(averageSubjectGrades);

		return dashboardDto;
	}

	public List<Student> listAllStudents() {
		return studentRepository.findAll();
	}

	public void saveStudent(Student student) {
		studentRepository.save(student);
	}
}
