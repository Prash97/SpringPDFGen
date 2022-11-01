package com.velocity.prashant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = (ResourceNotFoundException.class))
	public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
		String expMsg = rnfe.getMessage();
		String expCode = "EXP0025";
		
		ApiError error = new ApiError();
		error.setMsg(expMsg);
		error.setCode(expCode);
		
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);
		
	}
}
