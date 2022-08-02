package com.example.productService.core.errorhandling;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ProductServiceErrorHandler {
    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalException(IllegalStateException ex, WebRequest request){
        ErrroMessage errroMessage = new ErrroMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex ,  WebRequest request){
        ErrroMessage errroMessage = new ErrroMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<Object> handleCommandExecutionException(CommandExecutionException ex ,  WebRequest request){
        ErrroMessage errroMessage = new ErrroMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
