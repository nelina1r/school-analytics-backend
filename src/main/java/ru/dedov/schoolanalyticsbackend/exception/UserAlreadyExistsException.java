package ru.dedov.schoolanalyticsbackend.exception;

/**
 * Исключение, выбрасываемое в случае если, пользователь уже существует
 *
 * @author Alexander Dedov
 * @since 19.10.2024
 */
public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
