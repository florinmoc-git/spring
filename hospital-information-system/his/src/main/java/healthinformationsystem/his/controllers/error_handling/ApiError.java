package healthinformationsystem.his.controllers.error_handling;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The status property holds the operation call status, which will be anything from 4xx to signal client errors or 5xx
 * to signal server errors. A typical scenario is an HTTP code 400: BAD_REQUEST when the client, for example, sends an
 * improperly formatted field, like an invalid email address.
 *
 * The timestamp property holds the date-time instance when the error happened.
 *
 * The message property holds a user-friendly message about the error.
 *
 * The debugMessage property holds a system message describing the error in detail.
 *
 * The subErrors property holds an array of suberrors when there are multiple errors in a single call. An example would be
 * numerous validation errors in which multiple fields have failed.
 */
class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(HttpStatus status, String message, Throwable ex, List<ApiSubError> subErrors) {
        this();
        this.status = status;
        this.message = message;
        this.subErrors = subErrors;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<ApiSubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<ApiSubError> subErrors) {
        this.subErrors = subErrors;
    }
}
