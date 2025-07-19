package cv.igrp.simple.shared.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.util.Map;

public class IgrpResponseStatusException extends ErrorResponseException {

    public IgrpResponseStatusException(HttpStatusCode status) {
        super(status);
    }

    public IgrpResponseStatusException(HttpStatusCode status, ProblemDetail body, Throwable cause) {
        super(status, body, cause);
    }

    public static IgrpResponseStatusException of(HttpStatus status) {
        return new IgrpResponseStatusException(status);
    }

    public static IgrpResponseStatusException of(HttpStatus status, String title) {
        var problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(title);
        return new IgrpResponseStatusException(status, problemDetail, null);
    }

    public static <T> IgrpResponseStatusException of(HttpStatus status, String title, T details) {
        var problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(title);
        problemDetail.setProperties(Map.of("details", details));
        return new IgrpResponseStatusException(status, problemDetail, null);
    }


    public static IgrpResponseStatusException notFound(String title) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(title);
        return new IgrpResponseStatusException(HttpStatus.NOT_FOUND, problemDetail, null);
    }
    public static IgrpResponseStatusException badRequest(String title) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(title);
        return new IgrpResponseStatusException(HttpStatus.BAD_REQUEST, problemDetail, null);
    }

}
