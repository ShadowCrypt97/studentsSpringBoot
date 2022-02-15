package com.example.projectSrpingboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handlerStudentNotFoundException(StudentNotFoundException studentNotFoundException, String message){
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, String message) {
        log.info(ex.getMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(MethodArgumentNotValidException ex,
                                                                    WebRequest request) {

        GeneralResponse<?> response = new GeneralResponse<>(false, "Internal Server Error", null);
        if (CollectionUtils.isEmpty(ex.getBindingResult().getFieldErrors())) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("");
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            errorMsg.append("[").append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage())
                    .append("]").append(System.lineSeparator());
        }
        log.info(fieldErrorList.toString());
        response = new GeneralResponse<>(false, errorMsg.toString(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
