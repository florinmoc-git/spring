package healthinformationsystem.his.controllers.error_handling;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
        String error = "Invalid arguments in JSON request";
        List<ApiSubError> apiValidationErrors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError ->
                        new ApiValidationError(fieldError.getObjectName(),fieldError.getField(),
                                fieldError.getRejectedValue(),fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, exception, apiValidationErrors));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(HttpServletRequest request, NoSuchElementException exception){
        String error = "No resource found at path: " + request.getRequestURI();
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, exception));
    }


    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
