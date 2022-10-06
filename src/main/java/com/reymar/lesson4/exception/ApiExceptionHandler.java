package com.reymar.lesson4.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);
	
	@ExceptionHandler(value = { LaptopNotFoundException.class })
	public ResponseEntity<Object> manejoApiException(LaptopNotFoundException e) {
		log.warn(e.getMessage());
		return ResponseEntity.notFound().build();
	}
	
}
