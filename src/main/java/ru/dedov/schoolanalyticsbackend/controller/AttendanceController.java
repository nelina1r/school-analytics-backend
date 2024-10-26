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
import ru.dedov.schoolanalyticsbackend.dto.CreateAttendanceRequest;
import ru.dedov.schoolanalyticsbackend.service.AttendanceService;

/**
 * Контроллер для работы с классами
 *
 * @author Alexander Dedov
 * @since 26.10.2024
 */
@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
@Tag(name = "Действия над посещениями")
public class AttendanceController {
	private final AttendanceService attendanceService;

	@Operation(summary = "Создать посещение (посещение либо прогул, читать доку к CreateAttendanceRequest)")
	@PostMapping()
	public ResponseEntity<?> createAttendance(@RequestBody @Valid CreateAttendanceRequest request) {
		attendanceService.saveAttendance(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
