package ru.dedov.schoolanalyticsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dedov.schoolanalyticsbackend.dto.SignUpRequest;
import ru.dedov.schoolanalyticsbackend.model.entity.Class;
import ru.dedov.schoolanalyticsbackend.model.entity.Teacher;
import ru.dedov.schoolanalyticsbackend.model.entity.User;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.Role;
import ru.dedov.schoolanalyticsbackend.model.repository.TeacherRepository;

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
		User user = User.builder()
			.fio(request.getFio())
			.email(request.getEmail())
			.password(passwordEncoder.encode(request.getPassword()))
			.role(Role.ROLE_TEACHER)
			.build();
		userService.createUser(user);
	}
}
