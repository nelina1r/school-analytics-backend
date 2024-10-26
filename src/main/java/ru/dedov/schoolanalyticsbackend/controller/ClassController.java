package ru.dedov.schoolanalyticsbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dedov.schoolanalyticsbackend.dto.CreateClassRequest;
import ru.dedov.schoolanalyticsbackend.service.ClassService;

/**
 * Контроллер для работы с классами
 *
 * @author Alexander Dedov
 * @since 26.10.2024
 */
@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
@Tag(name = "Действия над классами")
public class ClassController {
	private final ClassService classService;

	@Operation(summary = "Создать класс")
	@PostMapping()
	public ResponseEntity<?> createClass(@RequestBody @Valid CreateClassRequest request) {
		classService.createClass(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Получить все классы")
	@GetMapping()
	public ResponseEntity<?> listAllClasses() {
		return new ResponseEntity<>(classService.getAllClasses(), HttpStatus.OK);
	}
}
