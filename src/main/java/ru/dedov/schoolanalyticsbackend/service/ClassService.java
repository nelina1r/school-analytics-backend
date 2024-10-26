package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dedov.schoolanalyticsbackend.dto.CreateClassRequest;
import ru.dedov.schoolanalyticsbackend.model.entity.Class;
import ru.dedov.schoolanalyticsbackend.model.repository.ClassRepository;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Сервис для классов
 *
 * @author Alexander Dedov
 * @since 26.10.2024
 */
@Service
@RequiredArgsConstructor
public class ClassService {

	private final ClassRepository classRepository;

	public void createClass(CreateClassRequest request) {
		Class classEntity = new Class();
		classEntity.setName(request.getName());
		saveClass(classEntity);
	}

	public void saveClass(Class classEntity) {
		if (classRepository.existsByNameIgnoreCase(classEntity.getName())) {
			throw new NoSuchElementException("Класс с названием " + classEntity.getName() + " уже существует");
		}
		classRepository.save(classEntity);
	}

	public List<Class> getAllClasses() {
		return classRepository.findAll();
	}
}
