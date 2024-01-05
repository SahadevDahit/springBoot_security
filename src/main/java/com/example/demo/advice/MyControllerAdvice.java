package com.example.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.NoSuchElementException;


@RestControllerAdvice
public class MyControllerAdvice {
	@ExceptionHandler(NoSuchElementException.class)

	public ResponseEntity<String> handleNotFoundException(NoSuchElementException ex) {
		String message = ex.getMessage();
		System.out.println("Handling NotFoundException: " + message);
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
}
