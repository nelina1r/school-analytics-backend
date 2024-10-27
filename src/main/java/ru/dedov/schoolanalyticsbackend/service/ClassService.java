package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dedov.schoolanalyticsbackend.dto.AssignToClassRequest;
import ru.dedov.schoolanalyticsbackend.dto.CreateClassRequest;
import ru.dedov.schoolanalyticsbackend.model.entity.Class;
import ru.dedov.schoolanalyticsbackend.model.entity.Student;
import ru.dedov.schoolanalyticsbackend.model.entity.Teacher;
import ru.dedov.schoolanalyticsbackend.model.repository.ClassRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.StudentRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.TeacherRepository;

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
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;

	@Transactional
	public void assignTeacherToClass(AssignToClassRequest request) {
		Class clazz = classRepository.findByNameIgnoreCase(request.getClassName());
		Teacher teacher = teacherRepository.findById(request.getUserId())
			.orElseThrow(() -> new NoSuchElementException("Учитель не найден"));
		clazz.setTeacher(teacher);
		teacher.getAssignedClasses().add(clazz);
		classRepository.save(clazz);
		teacherRepository.save(teacher);
	}

	@Transactional
	public void assignStudentToClass(AssignToClassRequest request) {
		Class clazz = classRepository.findByNameIgnoreCase(request.getClassName());
		Student student = studentRepository.findById(request.getUserId())
			.orElseThrow(() -> new NoSuchElementException("Студент не найден"));
		clazz.getStudents().add(student);
		student.setAClass(clazz);
		classRepository.save(clazz);
		studentRepository.save(student);
	}

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
