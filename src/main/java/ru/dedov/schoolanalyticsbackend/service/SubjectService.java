package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dedov.schoolanalyticsbackend.dto.CreateSchoolSubjectRequest;
import ru.dedov.schoolanalyticsbackend.exception.AlreadyExistsException;
import ru.dedov.schoolanalyticsbackend.model.entity.Subject;
import ru.dedov.schoolanalyticsbackend.model.repository.SubjectRepository;

import java.util.List;

/**
 * Сервис для школьных предметов
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Service
@RequiredArgsConstructor
public class SubjectService {

	private final SubjectRepository subjectRepository;

	/**
	 * Создать школьный предмет
	 */
	public void createSubject(CreateSchoolSubjectRequest request) {
		Subject subject = new Subject();
		subject.setName(request.getName());
		saveSubject(subject);
	}

	/**
	 * Сохранить предмет. Проверяет, чтобы аналогичного предмета не существовало, иначе - выбрасывается ошибка
	 */
	public void saveSubject(Subject subject) {
		if (subjectRepository.existsByNameIgnoreCase(subject.getName())) {
			throw new AlreadyExistsException("Школьный предмет с названием " + subject.getName() + " уже существует");
		}
		subjectRepository.save(subject);
	}

	public List<Subject> getAllSchoolSubjects() {
		return subjectRepository.findAll();
	}
}
