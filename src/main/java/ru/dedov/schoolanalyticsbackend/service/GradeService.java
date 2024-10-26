package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dedov.schoolanalyticsbackend.dto.CreateGradeRequest;
import ru.dedov.schoolanalyticsbackend.model.entity.Grade;
import ru.dedov.schoolanalyticsbackend.model.entity.Subject;
import ru.dedov.schoolanalyticsbackend.model.entity.Student;
import ru.dedov.schoolanalyticsbackend.model.repository.GradeRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.SubjectRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.StudentRepository;

import java.util.NoSuchElementException;

/**
 * Сервис для оценок
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Service
@RequiredArgsConstructor
public class GradeService {

	private final GradeRepository gradeRepository;
	private final StudentRepository studentRepository;
	private final SubjectRepository subjectRepository;

	/**
	 * Сохранить оценку
	 */
	public void saveGrade(CreateGradeRequest request) {
		if (!studentRepository.existsById(request.getStudentId())) {
			throw new NoSuchElementException("Ученик с Id = " + request.getStudentId() + " не найден");
		}
		if (!subjectRepository.existsByNameIgnoreCase(request.getSubjectName())) {
			throw new NoSuchElementException("Предмет с названием = " + request.getSubjectName() + " не найден");
		}
		Student student = studentRepository.findById(request.getStudentId()).orElseThrow(NoSuchElementException::new);
		Subject subject = subjectRepository.findByNameIgnoreCase(request.getSubjectName())
			.orElseThrow(NoSuchElementException::new);
		Grade grade = new Grade();
		grade.setStudent(student);
		grade.setSubject(subject);
		grade.setDate(request.getDateTime());
		grade.setValue(request.getValue());
		gradeRepository.save(grade);
	}
}
