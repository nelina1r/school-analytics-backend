package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dedov.schoolanalyticsbackend.dto.CreateAttendanceRequest;
import ru.dedov.schoolanalyticsbackend.dto.CreateGradeRequest;
import ru.dedov.schoolanalyticsbackend.model.entity.Attendance;
import ru.dedov.schoolanalyticsbackend.model.entity.Grade;
import ru.dedov.schoolanalyticsbackend.model.entity.Student;
import ru.dedov.schoolanalyticsbackend.model.entity.Subject;
import ru.dedov.schoolanalyticsbackend.model.repository.AttendanceRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.StudentRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.SubjectRepository;

import java.util.NoSuchElementException;

/**
 * Сервис для посещений
 *
 * @author Alexander Dedov
 * @since 26.10.2024
 */
@Service
@RequiredArgsConstructor
public class AttendanceService {
	private final AttendanceRepository attendanceRepository;
	private final StudentRepository studentRepository;
	private final SubjectRepository subjectRepository;

	/**
	 * Сохранить посещение
	 */
	public void saveAttendance(CreateAttendanceRequest request) {
		if (!studentRepository.existsById(request.getStudentId())) {
			throw new NoSuchElementException("Ученик с Id = " + request.getStudentId() + " не найден");
		}
		if (!subjectRepository.existsByNameIgnoreCase(request.getSubjectName())) {
			throw new NoSuchElementException("Предмет с названием = " + request.getSubjectName() + " не найден");
		}
		Student student = studentRepository.findById(request.getStudentId()).orElseThrow(NoSuchElementException::new);
		Subject subject = subjectRepository.findByNameIgnoreCase(request.getSubjectName())
			.orElseThrow(NoSuchElementException::new);
		Attendance attendance = new Attendance();
		attendance.setStudent(student);
		attendance.setSubject(subject);
		attendance.setDate(request.getDateTime());
		attendance.setStatus(request.getAttendanceStatus());
		attendanceRepository.save(attendance);
	}
}
