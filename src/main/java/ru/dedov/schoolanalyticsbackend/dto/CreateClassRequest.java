package ru.dedov.schoolanalyticsbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO запроса на создание класса
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Data
@Schema(description = "Запрос на создание класса")
public class CreateClassRequest {

	@Schema(description = "Наименование класса", example = "8Б")
	@NotBlank(message = "Наименование класса не должно быть пустым")
	private String name;
}
