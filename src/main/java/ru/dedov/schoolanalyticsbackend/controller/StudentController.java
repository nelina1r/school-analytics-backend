package ru.dedov.schoolanalyticsbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dedov.schoolanalyticsbackend.service.StudentService;

/**
 * Контроллер для учителей
 *
 * @author Alexander Dedov
 * @since 26.10.2024
 */
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Tag(name = "Действия учеников")
public class StudentController {
	private final StudentService studentService;

	@Operation(summary = "Получить дашборд текущего залогиненного ученика (ничего передавать не надо так как инфа из " +
		"JWT токена")
	@GetMapping("/my-dashboard")
	public ResponseEntity<?> showMyDashboard() {
		return new ResponseEntity<>(studentService.getMyDashboard(), HttpStatus.OK);
	}

	@Operation(summary = "Получить всех учеников")
	@GetMapping()
	public ResponseEntity<?> listAllStudents() {
		return new ResponseEntity<>(studentService.listAllStudents(), HttpStatus.OK);
	}
}
