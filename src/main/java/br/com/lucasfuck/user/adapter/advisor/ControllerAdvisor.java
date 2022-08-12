package br.com.lucasfuck.user.adapter.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Set;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();

        for (ConstraintViolation<?> violation : constraintViolations)
            stringBuilder.append(violation.getMessage()).append(" ");

        return ResponseEntity.badRequest().body(new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                stringBuilder.toString()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleConstraintException(MethodArgumentNotValidException exception) {
        StringBuilder stringBuilder = new StringBuilder();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors())
            stringBuilder.append(fieldError.getDefaultMessage());

        return ResponseEntity.badRequest().body(new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                stringBuilder.toString()));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintException() {
        return ResponseEntity.badRequest().body(new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Resource already exists"));
    }

}
