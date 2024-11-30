package banquemisr.challenge05.Task.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import java.util.HashMap;
import java.util.Map;

// This will be a global exception handler for validation errors
@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        // storing validation errors in the map
        Map<String, String> errors = new HashMap<>();

        // iterate over the field errors and get the field name and error message
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()) // Error field and the message
        );

        // Return the error map with httpstatus as bad request
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }



}
