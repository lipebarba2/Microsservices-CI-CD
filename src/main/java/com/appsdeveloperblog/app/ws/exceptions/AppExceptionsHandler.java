package com.appsdeveloperblog.app.ws.exceptions;

import com.appsdeveloperblog.app.ws.model.request.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler implements IAppExceptionsHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timestamp(new Date())
                .message(getSafeErrorMessage(ex))
                .build();

        log.error("Unhandled Exception: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({NullPointerException.class, RuntimeException.class, UserServiceExceptions.class})
    public ResponseEntity<Object> handleSpecificExceptions(RuntimeException ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timestamp(new Date())
                .message(getSafeErrorMessage(ex))
                .build();

        log.error("Runtime Exception: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .timestamp(new Date())
                .message("Validation failed")
                .errors(errors)
                .build();

        log.error("Validation failed: {}", errors, ex);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    private String getSafeErrorMessage(Exception ex) {
        return ex.getLocalizedMessage() == null || ex.getLocalizedMessage().isEmpty()
                ? "An unexpected error occurred"
                : ex.getLocalizedMessage();
    }
}
