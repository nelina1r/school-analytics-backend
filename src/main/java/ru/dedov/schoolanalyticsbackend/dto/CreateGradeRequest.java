package ru.dedov.schoolanalyticsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO запроса на выставление оценки
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Data
@Schema(description = "Запрос на выставление оценки")
public class CreateGradeRequest {

	@JsonProperty("student_id")
	@Schema(description = "Id ученика", example = "9")
	private Long studentId;
	@JsonProperty("subject_name")
	@Schema(description = "Наименование школьного предмета", example = "Физика")
	private String subjectName;
	@Min(value = 1, message = "Оценка не может быть меньше 1")
	@Max(value = 5, message = "Оценка не может быть больше 5")
	@Schema(description = "Непосредственно оценка (от 1 до 5)", example = "4")
	private Integer value;
	@Schema(description = "Дата и время выставления оценки", example = "2024-10-25T12:00:00")
	private LocalDateTime dateTime;
}
