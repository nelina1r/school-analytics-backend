package ru.dedov.schoolanalyticsbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dedov.schoolanalyticsbackend.dto.CreateGradeRequest;
import ru.dedov.schoolanalyticsbackend.service.GradeService;

/**
 * Контроллер для работы с оценками
 *
 * @author Alexander Dedov
 * @since 26.10.2024
 */
@RestController
@RequestMapping("/grade")
@RequiredArgsConstructor
@Tag(name = "Действия над оценками")
public class GradeController {
	private final GradeService gradeService;

	@Operation(summary = "Создать оценку")
	@PostMapping()
	public ResponseEntity<?> createGrade(@RequestBody @Valid CreateGradeRequest request) {
		gradeService.saveGrade(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
