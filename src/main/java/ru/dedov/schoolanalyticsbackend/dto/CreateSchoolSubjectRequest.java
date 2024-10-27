package ru.dedov.schoolanalyticsbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO Запроса на создание школьного предмета
 *
 * @author Alexander Dedov
 * @since 25.10.2024
 */
@Data
@Schema(description = "Запрос на создание школьного предмета")
public class CreateSchoolSubjectRequest {

	@Schema(description = "Наименование школьного предмета", example = "Физика")
	@NotBlank(message = "Наименование школьного предмета не должно быть пустым")
	private String name;
}
