package com.dnb.userservice.advice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dnb.userservice.exceptions.IdNotFoundException;

public class AppAdvice {

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Id Provided")
	@ExceptionHandler(IdNotFoundException.class)
	public void invalidAccountIdExceptionHandler(IdNotFoundException idNotFoundException) {

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleException(HttpRequestMethodNotSupportedException e)
			throws IOException {

		List<String> supported = List.of(e.getSupportedMethods());
		String provided = e.getMethod();

		String error = provided + " is not one of the supported Http Method (" + String.join(", ", supported) + ")";

		Map<String, String> errorResponse = Map.of("error", error, "message", e.getLocalizedMessage(), "status",
				HttpStatus.METHOD_NOT_ALLOWED.toString());

		return new ResponseEntity<Map<String, String>>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, HttpRequest request) {

		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", LocalDate.now());
		responseBody.put("status", status.value());

		Map<String, String> errors2 = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

		responseBody.put("errors", errors2);

		return new ResponseEntity<Object>(responseBody, headers, status);
	}
}
