package com.library_management.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        // get errors key
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((item)->{
            errors.put(item.getField(),item.getDefaultMessage());
        });
        Map<String,Object> res = new HashMap<>();
        res.put("success",false);
        res.put("code",HttpStatus.BAD_REQUEST.value()); // 400
        res.put("message","Validation Failed");
        res.put("data",null);
        res.put("errors",errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponse.error("Something Wrong!",HttpStatus.INTERNAL_SERVER_ERROR));
    }

//    @ExceptionHandler(APIException.class)
//    public ResponseEntity<NotExists> myAPIException(APIException e){
//        String message = e.getMessage();
//        NotExists notExists = new NotExists(message, false);
//        return new ResponseEntity<>(notExists, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> myResourceNotFoundException(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(APIResponse.error(e.getMessage(),HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> apiException(APIException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(APIResponse.error(e.getMessage(),HttpStatus.BAD_REQUEST));
    }

}
