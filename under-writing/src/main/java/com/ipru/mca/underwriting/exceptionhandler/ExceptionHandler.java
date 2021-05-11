package com.ipru.mca.underwriting.exceptionhandler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;


import com.ipru.mca.underwriting.model.UWException;

@ControllerAdvice
public class ExceptionHandler {
	Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationHandler(MethodArgumentNotValidException e)
	{
		log.error("error occured,Message:"+e.getMessage() +e.getStackTrace());
		UWException exception = new UWException(new Date(), e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST.toString());
	 	
	 	return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<?> getglobalHandler(Exception e)
	{
		log.error("error occured,Message:"+e.getMessage() +e.getStackTrace());

		UWException ex = new UWException(new Date(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
