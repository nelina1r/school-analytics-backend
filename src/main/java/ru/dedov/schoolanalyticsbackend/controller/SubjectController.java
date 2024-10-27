package ru.dedov.schoolanalyticsbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dedov.schoolanalyticsbackend.dto.CreateSchoolSubjectRequest;
import ru.dedov.schoolanalyticsbackend.service.SubjectService;

/**
 * Контроллер для работы со школьными предметами
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
@Tag(name = "Действия над школьными предметами")
public class SubjectController {
	private final SubjectService subjectService;

	@Operation(summary = "Создать школьный предмет")
	@PostMapping()
	public ResponseEntity<?> createSchoolSubject(@RequestBody @Valid CreateSchoolSubjectRequest request) {
		subjectService.createSubject(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Получить все школьные предметы")
	@GetMapping()
	public ResponseEntity<?> listAllSchoolSubjects() {
		return new ResponseEntity<>(subjectService.getAllSchoolSubjects(), HttpStatus.OK);
	}
}
