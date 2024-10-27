package ru.dedov.schoolanalyticsbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dedov.schoolanalyticsbackend.dto.AssignToClassRequest;
import ru.dedov.schoolanalyticsbackend.service.ClassService;
import ru.dedov.schoolanalyticsbackend.service.TeacherService;

/**
 * Контроллер для учителей
 *
 * @author Alexander Dedov
 * @since 26.10.2024
 */
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Tag(name = "Действия учителей")
public class TeacherController {
	private final ClassService classService;
	private final TeacherService teacherService;

	@Operation(summary = "Получить дашборд по классу для текущего залогиненного учителя, передать айди учебного класса")
	@GetMapping("/class-dashboard/{classId}")
	public ResponseEntity<?> showMyDashboard(@PathVariable Long classId) {
		return new ResponseEntity<>(teacherService.getClassDashboard(classId), HttpStatus.OK);
	}

	@Operation(summary = "Получить список моих классов (логиниться под учеткой учителя)")
	@GetMapping("/my-classes")
	public ResponseEntity<?> showMyClasses() {
		return new ResponseEntity<>(teacherService.listMyClasses(), HttpStatus.OK);
	}

	@Operation(summary = "Прикрепить учителя к классу")
	@PostMapping("/link")
	public ResponseEntity<?> linkTeacherToClass(@RequestBody @Valid AssignToClassRequest request) {
		classService.assignTeacherToClass(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
