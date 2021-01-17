package br.com.digitalzyon.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationErros(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		
		List<String> errorMessages = bindingResult.getAllErrors()
				.stream()
				.map(objectError -> objectError.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ApiError(errorMessages);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String errorMessage = ex.getReason();
		HttpStatus statusCode = ex.getStatus();
		ApiError apiError = new ApiError(errorMessage);
		return new ResponseEntity(apiError, statusCode);
	}

}
