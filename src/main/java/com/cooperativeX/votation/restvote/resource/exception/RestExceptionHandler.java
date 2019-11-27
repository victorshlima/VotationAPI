 package com.cooperativeX.votation.restvote.resource.exception;

import com.cooperativeX.votation.restvote.Exception.NotExistDaoException;
import com.cooperativeX.votation.restvote.domain.DetailError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


 @ControllerAdvice
 public class RestExceptionHandler extends ResponseEntityExceptionHandler {

     @ExceptionHandler({
             NullPointerException.class,
             IllegalArgumentException.class,
             javax.persistence.NoResultException.class
     })

     public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request) {
         logger.error(ex.getMessage());
         return handleExceptionInternal(
                 ex, DetailError.builder()
                         .addDetail("Generic Error")
                         .addErro(ex.getMessage())
                         .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                         .addHttpMethod(getHttpMethod(request))
                         .addPath(getPath(request))
                         .build(),
                 new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
     }

     @ExceptionHandler({
             org.hibernate.exception.ConstraintViolationException.class
     })
     public ResponseEntity<Object> constraintViolated(org.hibernate.exception.ConstraintViolationException ex,
                                                     WebRequest request) {
         logger.error(ex.getMessage());
         return handleExceptionInternal(
                 ex, DetailError.builder()
                         .addDetail("Violated constraint: " + ex.getConstraintName())
                         .addErro(ex.getMessage())
                         .addStatus(HttpStatus.CONFLICT)
                         .addHttpMethod(getHttpMethod(request))
                         .addPath(getPath(request))
                         .build(),
                 new HttpHeaders(), HttpStatus.CONFLICT, request);
     }

     @ExceptionHandler({
             org.hibernate.PropertyValueException.class
     })
     public ResponseEntity<Object> nullField(org.hibernate.PropertyValueException ex, WebRequest request) {
         logger.error(ex.getMessage());
         return handleExceptionInternal(
                 ex, DetailError.builder()
                         .addDetail("The field'" + ex.getPropertyName() + "' is mandatory.")
                         .addErro(ex.getMessage())
                         .addStatus(HttpStatus.BAD_REQUEST)
                         .addHttpMethod(getHttpMethod(request))
                         .addPath(getPath(request))
                         .build(),
                 new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
     }

     @ExceptionHandler({
             NotExistDaoException.class
     })
     public ResponseEntity<Object> entityNotFound(NotExistDaoException ex, WebRequest request) {
         logger.error(ex.getMessage());
         return handleExceptionInternal(
                 ex, DetailError.builder()
                         .addDetail("Resource not found")
                         .addErro(ex.getMessage())
                         .addStatus(HttpStatus.NOT_FOUND)
                         .addHttpMethod(getHttpMethod(request))
                         .addPath(getPath(request))
                         .build(),
                 new HttpHeaders(),
                 HttpStatus.NOT_FOUND,
                 request);
     }

     private String getPath(WebRequest request) {
         return ((ServletWebRequest) request).getRequest().getRequestURI();
     }

     private String getHttpMethod(WebRequest request) {
         return ((ServletWebRequest) request).getRequest().getMethod();
     }

 }