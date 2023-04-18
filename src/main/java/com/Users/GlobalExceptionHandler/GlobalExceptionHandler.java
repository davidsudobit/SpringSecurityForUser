package com.Users.GlobalExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Users.CustomException.AlreadyExistsException;
import com.Users.CustomException.InsufficientException;
import com.Users.CustomException.NotAuthorizedException;
import com.Users.CustomException.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value=NotFoundException.class)
	public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=NotAuthorizedException.class)
	public ResponseEntity<String> notAuthorizedExceptionHandler(NotAuthorizedException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value=InsufficientException.class)
	public ResponseEntity<String> insufficientExceptionHandler(InsufficientException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.PARTIAL_CONTENT);
	}
	
	@ExceptionHandler(value=AlreadyExistsException.class)
	public ResponseEntity<String> alreadyExistsExceptionHandler(AlreadyExistsException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> exceptionHandler(MethodArgumentNotValidException e){
		Map<String,String> m1=new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach((fieldError)->m1.put(fieldError.getField(), fieldError.getDefaultMessage()));
		return new ResponseEntity<>(m1,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}
