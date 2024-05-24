package com.vetApp.VetApp.core.config;

import com.vetApp.VetApp.core.exception.ConflictException;
import com.vetApp.VetApp.core.exception.MinuteException;
import com.vetApp.VetApp.core.exception.NoVaccineException;
import com.vetApp.VetApp.core.exception.NotFoundException;
import com.vetApp.VetApp.core.result.Result;
import com.vetApp.VetApp.core.result.ResultData;
import com.vetApp.VetApp.core.utilities.Msg;
import com.vetApp.VetApp.core.utilities.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice

public class GlobalExceptionHandler {  //Catching the exceptions
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Result> handleConflictException(ConflictException e) {
        return new ResponseEntity<>(ResultHelper.conflictError(e.getMessage()), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MinuteException.class)
    public ResponseEntity<Result> handleMinuteException(MinuteException e) {
        return new ResponseEntity<>(ResultHelper.minuteError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoVaccineException.class)
    public ResponseEntity<Result> handleNoVaccineException(NoVaccineException e) {
        return new ResponseEntity<>(ResultHelper.successVaccine(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream().
                map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ResultData<List<String>> resultData = new ResultData<>(false, Msg.VALIDATE_ERROR, "400",validationErrorList );
        return new ResponseEntity<>(resultData, HttpStatus.BAD_REQUEST);
    }
}
