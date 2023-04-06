package patienttriagesystem.pts.controllers.error_handling;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handleConnectException(ConnectException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.GATEWAY_TIMEOUT);
        problemDetail.setDetail("Could not connect to services. Please try again later.");
        problemDetail.setProperty("message", exception.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Object> handleWebClientResponseException(WebClientResponseException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatus(exception.getRawStatusCode());
        problemDetail.setProperty("message", exception.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }
}
