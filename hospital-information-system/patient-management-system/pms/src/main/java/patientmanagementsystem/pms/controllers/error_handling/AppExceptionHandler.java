package patientmanagementsystem.pms.controllers.error_handling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ApiValidationError> apiValidationErrors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError ->
                        new ApiValidationError(fieldError.getObjectName(), fieldError.getField(),
                                fieldError.getRejectedValue(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Invalid arguments in JSON request");
        problemDetail.setProperty("entity", exception.getObjectName());
        problemDetail.setProperty("subErrors", apiValidationErrors);
        return ResponseEntity.of(problemDetail).build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Malformed JSON request");
        problemDetail.setProperty("message", exception.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setDetail("No resource found at path: " + request.getRequestURI());
        problemDetail.setProperty("message", exception.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {
        List<ApiValidationError> apiValidationErrors = exception.getConstraintViolations()
                .stream()
                .map(violation ->
                        new ApiValidationError(violation.getRootBean().getClass().getSimpleName(), violation.getPropertyPath().toString(),
                                violation.getInvalidValue(), violation.getMessageTemplate()))
                .collect(Collectors.toList());
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Constraint violation updating resource at path: " + request.getRequestURI());
        problemDetail.setProperty("message", exception.getMessage());
        problemDetail.setProperty("subErrors", apiValidationErrors);
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler({JsonPatchException.class, JsonProcessingException.class})
    public ResponseEntity<Object> handleJsonPatchProcessingExceptions(){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("message", "Could not parse JSON payload!");
        return ResponseEntity.of(problemDetail).build();
    }

}
