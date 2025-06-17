package com.receipe_rest_api.receipe_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExpectionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handlerforResourceNotFoundException(ResourceNotFoundException expection){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
						"Time : ", LocalDateTime.now(),
						"Status : ", 404,
						"Error : ", "Resource Not Found",
						"Message : ", expection.getMessage()				
						));		
	}	
}
