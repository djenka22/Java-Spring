package com.fonis.todo.infrastructure.exceptions;


import com.fonis.todo.infrastructure.exceptions.custom.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice //treba nam da bi spring znao da zelimo da uhvatimo exception-e da ga ne bi bacio u konzoli nego prvo da proveri da li smo ga obradili negde
public class ApiExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> onConstraintViolationException(ConstraintViolationException ex){
        Set<Violation> violations = new HashSet<>();
        for(ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()){
            Violation violation = new Violation(
                    constraintViolation.getPropertyPath().toString(),
                    constraintViolation.getMessage(),
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
            violations.add(violation);
        }
        ErrorResponse errors = new ErrorResponse(violations);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException ex){
        Set<Violation> violations = new HashSet<>();

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            Violation violation = new Violation(
                    fieldError.getField(),
                    fieldError.getDefaultMessage(),
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
            violations.add(violation);
        }
        return new ErrorResponse(violations);
    }


    @ExceptionHandler(value = {ProjectNotFoundException.class})
    public ResponseEntity<CustomException> onNotFoundException(RuntimeException ex){
                CustomException exception = new CustomException(
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND,
                        ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

}

record Violation(String field, String message, ZonedDateTime timestamp){}

record ErrorResponse(Set<Violation> violations){}

record CustomException(String message, HttpStatus http, ZonedDateTime timestamp){}