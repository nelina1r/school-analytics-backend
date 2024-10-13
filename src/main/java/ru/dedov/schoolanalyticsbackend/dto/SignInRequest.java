package ru.dedov.schoolanalyticsbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO запроса на аутентификацию
 *
 * @author Alexander Dedov
 * @since 13.10.2024
 */
@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {

	@Schema(description = "Имя пользователя", example = "asidorov")
	@Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
	@NotBlank(message = "Имя пользователя не может быть пустыми")
	private String username;

	@Schema(description = "Пароль", example = "my_1secret1_password!")
	@Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
	@NotBlank(message = "Пароль не может быть пустыми")
	private String password;
}