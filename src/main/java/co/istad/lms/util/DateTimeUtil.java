package co.istad.lms.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {

    public static LocalTime stringToLocalTime(String timeString,String field){
        LocalTime time = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            time = LocalTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("%s = %s is not valid format", field,timeString));
        }
        return time;
    }

    public static LocalDate stringToLocalDate(String timeString,String field){
        LocalDate date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = LocalDate.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("%s = %s is not valid format", field,timeString));
        }
        return date;
    }

    public static LocalDateTime stringToLocalDateTime(String timeString, String field){
        LocalDateTime dateTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            dateTime = LocalDateTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("%s = %s is not valid format", field,timeString));
        }
        return dateTime;
    }
}
