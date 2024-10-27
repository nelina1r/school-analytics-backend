package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dedov.schoolanalyticsbackend.dto.SignUpRequest;
import ru.dedov.schoolanalyticsbackend.exception.UserAlreadyExistsException;
import ru.dedov.schoolanalyticsbackend.model.entity.Class;
import ru.dedov.schoolanalyticsbackend.model.entity.Teacher;
import ru.dedov.schoolanalyticsbackend.model.entity.User;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.Role;
import ru.dedov.schoolanalyticsbackend.model.repository.TeacherRepository;
import ru.dedov.schoolanalyticsbackend.model.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

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
