package com.neoris.turnosrotativos.exceptions;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        responseBody.put("errors", errors);

        return new ResponseEntity<>(responseBody, headers, status);
    }
	
	/*
	 * Maneja la excepcion Bad_Request (400)
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleCustomParameterConstraintExceptions(BadRequestException pCE, WebRequest request){
		Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("message", pCE.getMessage());
		return new ResponseEntity<Object>(responseBody, HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * Maneja la excepcion Conflict (409)
	 */
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<Object> handleEntityConflictException(ConflictException eAEE, WebRequest request){
		Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.CONFLICT.value());
        responseBody.put("message", eAEE.getMessage());
		return new ResponseEntity<Object>(responseBody, HttpStatus.CONFLICT);
	}
	
	/*
	 * Maneja la excepcion Not_Found (404)
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(NotFoundException nFE, WebRequest request){
		Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("message", nFE.getMessage());
		return new ResponseEntity<Object>(responseBody, HttpStatus.NOT_FOUND);
	}
}
