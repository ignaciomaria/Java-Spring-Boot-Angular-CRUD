package com.neoris.turnosrotativos.exceptions;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException{
	
	public BadRequestException(String message) {super(message);}
}
