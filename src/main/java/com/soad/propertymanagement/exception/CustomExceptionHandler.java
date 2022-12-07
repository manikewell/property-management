package com.soad.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv) {
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();
        List<ErrorModel> errorModelList = new ArrayList<>();

        for (FieldError fe : fieldErrorList) {
            logger.info("Inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            logger.debug("Inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex) {
        for(ErrorModel err: bex.getErrors()){
            logger.info("info - Business Exception is thrown: {} - {}", err.getCode(), err.getMessage());
            logger.debug("debug - Business Exception is thrown: {} - {}", err.getCode(), err.getMessage());
            logger.warn("warn - Business Exception is thrown: {} - {}", err.getCode(), err.getMessage());
            logger.error("error - Business Exception is thrown: {} - {}", err.getCode(), err.getMessage());
        }
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
