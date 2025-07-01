package cv.igrp.simple.shared.domain.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(IgrpResponseStatusException.class)
    public ProblemDetail handleIgrpResponseStatusException(IgrpResponseStatusException ex) {

        LOGGER.error(ex.getMessage(), ex);

        return ex.getBody();
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

        LOGGER.error("HTTP MESSAGE NOT READABLE EXCEPTION", ex);

        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        if (ex.getCause() instanceof InvalidFormatException ife && ife.getTargetType().isEnum()) {

            var targetType = ife.getTargetType();

            var allowedValues = Arrays.stream(targetType.getEnumConstants())
                    .map(Object::toString)
                    .toArray(String[]::new);

            problem.setTitle("Invalid value for enum type: " + targetType.getSimpleName());
            problem.setProperty("CurrentValue", ife.getValue());
            problem.setProperty("AllowedValues", allowedValues);
            return problem;
        }

        problem.setTitle("Malformed JSON request");
        problem.setDetail(ex.getMessage());

        return problem;
    }



}