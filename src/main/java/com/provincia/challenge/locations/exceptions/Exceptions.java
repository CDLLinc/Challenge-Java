package com.provincia.challenge.locations.exceptions;

import com.provincia.challenge.messages.Message;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class Exceptions {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e) {
        String error = "Error calling AccuWeather API: {} ";
        log.error(error.concat(e.getMessage()), e);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Message(error.concat(e.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        String error = "Error processing request: {} ";
        log.error(error, e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(error.concat(e.getMessage())));
    }
}
