package com.stackroute.music.controller;

import com.stackroute.music.exceptions.TrackAlreadyExistingException;
import com.stackroute.music.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<String> trackNotFoundException(TrackNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackAlreadyExistingException.class)
    public ResponseEntity<String> trackAlreadyExists(TrackAlreadyExistingException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
