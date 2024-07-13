package com.potatoes.bloodrecovery.exception;

import com.potatoes.bloodrecovery.interfaces.rest.controller.BloodRequestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.potatoes.constants.StaticValues.HTTP_STATUS;
import static com.potatoes.constants.StaticValues.RESULT_MESSAGE;

@Slf4j
@RestControllerAdvice(assignableTypes = BloodRequestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private HttpHeaders setHeaders(String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(RESULT_MESSAGE, URLEncoder.encode(message, StandardCharsets.UTF_8));
        headers.add(HTTP_STATUS, String.valueOf(status.value()));
        return headers;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        HttpHeaders httpHeaders = setHeaders(ex.getMessage(), HttpStatus.BAD_REQUEST);
        log.error("ConstraintViolationException: {}", ex.getMessage());
        return new ResponseEntity<>(httpHeaders,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleApiExceptions(ApiException ex){
        HttpHeaders httpHeaders = setHeaders(ex.getResultMessage(), ex.getHttpStatus());
        log.error("ApiException: {}", ex.getMessage());
        return new ResponseEntity<>(httpHeaders,ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        HttpHeaders httpHeaders = setHeaders(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        log.error("Exception: {}", ex.getMessage());
        return new ResponseEntity<>(httpHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
