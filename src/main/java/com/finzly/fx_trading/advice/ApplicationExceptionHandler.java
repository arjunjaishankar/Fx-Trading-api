package com.finzly.fx_trading.advice;

import java.util.HashMap;
import java.util.Map;

import com.finzly.fx_trading.exception.DataNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> exceptionHandler(MethodArgumentNotValidException exp){
		
		Map<String,String> errorMap = new HashMap<>();
		exp.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataNotFoundException.class)
	public Map<String,String> handleBusinessException(DataNotFoundException ex){
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put("Information", ex.getMessage());
		return errorMap;
	}
}
