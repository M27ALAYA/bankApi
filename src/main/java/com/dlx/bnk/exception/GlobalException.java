package com.dlx.bnk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalException  {

	  @ExceptionHandler(CustomerNotFoundException.class)
	    public ResponseEntity<com.dlx.bnk.dto.Error> handleCustomerNotFoundException(CustomerNotFoundException ex) {

		  return ResponseEntity.status(HttpStatus.NOT_FOUND)
		            					.body(new com.dlx.bnk.dto.Error(
		            								ex.getMessage(),
		            								HttpStatus.NOT_FOUND.value())
		            						);
	    }

	
}
