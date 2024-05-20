package co.istad.lms.exception;


import co.istad.lms.base.BasedResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalRestControllerAdviser {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BasedResponse<?> handleNoSuchElementException(NoSuchElementException ex) {
        return BasedResponse
                .notFound()
                .setMetadata(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BasedResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
        );
        return BasedResponse.badRequest().setMetadata(errors);
    }


    // handle all the exception like postgresqlexception
//    @ExceptionHandler()
//    public BaseResponse<?> handleDuplicateEntriesException() {
//        return BaseResponse
//                .badRequest()
//                .setMetadata("Duplicate entries");
//
//    }


    //    image exception , image size, image format ...
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        return new ResponseEntity<>(e.getReason(), e.getStatusCode());
    }

    //   handle all the exception like postgresqlexception
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BasedResponse<?> handlePSQLException(DataIntegrityViolationException ex) {
        System.out.println("ex = " + ex);
        return BasedResponse
                .badRequest()
                .setMetadata("Email and username must be unique!");
    }
//
}
