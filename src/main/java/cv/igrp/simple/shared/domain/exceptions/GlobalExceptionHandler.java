package cv.igrp.simple.shared.domain.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IgrpResponseStatusException.class)
    public ResponseEntity<Object> handleIgrpException(IgrpResponseStatusException ex) {
        IgrpProblem<?> problem = ex.getProblem();
        return ResponseEntity
                .status(problem.getStatus())
                .body(problem);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<Object> handleClassCastException(ClassCastException ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        StackTraceElement origin = stackTrace.length > 0 ? stackTrace[0] : null;
        String detailedMessage = ex.getMessage();
        if (origin != null) {
            detailedMessage += " at " + origin.getClassName() + "." + origin.getMethodName() +
                    "(" + origin.getFileName() + ":" + origin.getLineNumber() + ")";
        }
        IgrpProblem<?> problem = new IgrpProblem<>(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", detailedMessage);
        return ResponseEntity.status(problem.getStatus()).body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<IgrpProblem<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            //System.out.println("erros:::::::::: "+fieldError.getDefaultMessage());
        }
        IgrpProblem<Map<String, String>> problem = new IgrpProblem<>(
                HttpStatus.BAD_REQUEST,
                "Erros de validação entrada",
                errors
        );
        return ResponseEntity
                .status(problem.getStatus())
                .body(problem);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<IgrpProblem<Map<String, String>>> handleDateTimeParseException(DateTimeParseException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("dataNascimento", "Formato de data inválido. Use o formato yyyy-MM-dd");

        IgrpProblem<Map<String, String>> problem = new IgrpProblem<>(
                HttpStatus.BAD_REQUEST,
                "Erro de formatação de data",
                errors
        );

        return ResponseEntity
                .status(problem.getStatus())
                .body(problem);
    }



}