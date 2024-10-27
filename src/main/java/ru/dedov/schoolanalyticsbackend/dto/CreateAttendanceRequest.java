package ru.dedov.schoolanalyticsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.dedov.schoolanalyticsbackend.model.entity.enums.AttendanceStatus;

import java.time.LocalDateTime;

/**
 * DTO запроса на создание посещения
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Data
@Schema(description = "Запрос на создание посещения")
public class CreateAttendanceRequest {

	@JsonProperty("student_id")
	@Schema(description = "Id ученика", example = "9")
	private Long studentId;
	@JsonProperty("subject_name")
	@Schema(description = "Наименование школьного предмета", example = "Физика")
	private String subjectName;
	@JsonProperty("attendance_status")
	@Schema(description = "Статус посещения. Значения могут быть только: PRESENT либо ABSENT", example = "ABSENT")
	private AttendanceStatus attendanceStatus;
	@Schema(description = "Дата и время посещения (либо прогула)", example = "2024-10-25T12:00:00")
	private LocalDateTime dateTime;
}
