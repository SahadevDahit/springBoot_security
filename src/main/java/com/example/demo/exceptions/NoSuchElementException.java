package com.example.demo.exceptions;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class NoSuchElementException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	public NoSuchElementException(String message) {
		super();
		this.message = message;
	}
	public NoSuchElementException() {
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
