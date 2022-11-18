package com.example.MiniProjectGroup5.exception;

import com.example.MiniProjectGroup5.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception() {

        return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> exception2() {
        return new ResponseEntity<>("Community/Type not present", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> exception3() {
        return new ResponseEntity<>("Community/Type not present", HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler (value =  HttpMessageNotReadableException.class)
//    public ResponseEntity<Object> exception3(){
//        return new ResponseEntity<>("Usertype not present", HttpStatus.BAD_REQUEST);
//    }


}