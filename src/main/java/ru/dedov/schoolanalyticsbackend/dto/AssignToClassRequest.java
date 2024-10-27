package ru.dedov.schoolanalyticsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO запроса на прикрепление к классу
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Data
@AllArgsConstructor
@Schema(description = "Запрос на прикрепление к классу")
public class AssignToClassRequest {

	@JsonProperty("class_name")
	@Schema(description = "Наименование класса", example = "8Б")
	private String className;
	@JsonProperty("user_id")
	@Schema(description = "Id пользователя", example = "8")
	private Long userId;
}
