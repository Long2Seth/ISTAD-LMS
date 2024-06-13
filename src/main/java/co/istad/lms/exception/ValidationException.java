package co.istad.lms.exception;

import co.istad.lms.base.BasedError;
import co.istad.lms.base.BasedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BasedErrorResponse handleValidationErrors(MethodArgumentNotValidException ex) {

        List<Map<String, Object>> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, Object> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("reason", fieldError.getDefaultMessage());
            errors.add(error);
        });

        BasedError<List<Map<String, Object>>> basedError = new BasedError<>();
        basedError.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
        basedError.setDescription(errors);

        return new BasedErrorResponse(basedError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BasedErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        String message = (mostSpecificCause != null ? mostSpecificCause.getMessage() : ex.getMessage());

        List<Map<String, Object>> errors = new ArrayList<>();
        Map<String, Object> errorDetail = new HashMap<>();
        errorDetail.put("field", extractFieldNameFromMessage(message));
        errorDetail.put("reason", "Invalid input format" );
        errors.add(errorDetail);

        BasedError<List<Map<String, Object>>> basedError = new BasedError<>();
        basedError.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
        basedError.setDescription(errors);

        return new BasedErrorResponse(basedError);
    }

    private String extractFieldNameFromMessage(String message) {
        String fieldName = "N/A";
        String fieldPattern = "\\[\"(.*?)\"\\]";

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(fieldPattern);
        java.util.regex.Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            fieldName = matcher.group(1);
        }

        return fieldName;
    }
}
