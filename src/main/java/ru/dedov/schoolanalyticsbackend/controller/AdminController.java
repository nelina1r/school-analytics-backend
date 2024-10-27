package ru.dedov.schoolanalyticsbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dedov.schoolanalyticsbackend.dto.SignUpRequest;
import ru.dedov.schoolanalyticsbackend.service.TeacherService;

/**
 * Контроллер для действий администратора
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Администрирование")
public class AdminController {

	private final TeacherService teacherService;

	@Operation(summary = "Создать новый аккаунт учителя")
	@PostMapping("/new-teacher")
	public ResponseEntity<?> createNewTeacher(@RequestBody @Valid SignUpRequest request) {
		teacherService.createNewTeacher(request);
		return ResponseEntity.ok().build();
	}
}
