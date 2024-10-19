package ru.dedov.schoolanalyticsbackend.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dedov.schoolanalyticsbackend.dto.ErrorResponse;
import ru.dedov.schoolanalyticsbackend.exception.UserAlreadyExistsException;

/**
 * Централизованный обработчик исключений на уровне всего приложения
 *
 * @author Alexander Dedov
 * @since 19.10.2024
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @param ex исключение, выбрасываемое в случае если не пройдена валидация входных параметров
	 * @return ResponseEntity с ErrorResponse с текстом ошибки и кодом 404
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
		return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param ex исключение, выбрасываемое в случае если пользователь уже существует
	 * @return ResponseEntity с ErrorResponse с текстом ошибки и кодом 404
	 */
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
	}
}
