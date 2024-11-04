package co.istad.lms.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeUtil {

    public static LocalTime stringToLocalTime(String timeString,String field){
        LocalTime time = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateTime = LocalDateTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("%s = %s is not valid format", field,timeString));
        }

        return dateTime;
    }

    public static void validateAcademicYear(String academicYear) {
        // Define the regex pattern for yyyy-yyyy
        String regex = "^(\\d{4})-(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(academicYear);

        if (!matcher.matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("academicYear = %s is not valid format(year-year+1),ex 2024-2025",academicYear));
        }

        // Extract years
        int startYear = Integer.parseInt(matcher.group(1));
        int endYear = Integer.parseInt(matcher.group(2));

        // Check if end year is exactly one year greater than start year
        if (endYear != startYear + 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("academicYear = %s is not valid format(year-year+1),ex 2024-2025",academicYear));
        }

    }

    public static void validateAcademicYearAlias(String academicYear) {
        // Define the regex pattern for yyyy-yyyy or yyyy
        String regex = "^(\\d{4})-(\\d{4})|(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(academicYear);

        if (!matcher.matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("academicYearAlias = %s is not valid format (year-year+1 or yyyy), ex 2024-2025 or " +
                            "2024", academicYear));
        }

        // Check if the input is in the yyyy-yyyy format
        if (academicYear.contains("-")) {
            // Extract years
            int startYear = Integer.parseInt(matcher.group(1));
            int endYear = Integer.parseInt(matcher.group(2));

            // Check if end year is exactly one year greater than start year
            if (endYear != startYear + 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("academicYearAlias = %s is not valid format (year-year+1), ex 2024-2025",
                                academicYear));
            }
        }
    }


    public static void validateDiplomaSession(String diplomaSession) {
        // Define the regex pattern for yyyy-yyyy
        String regex = "^(\\d{4})-(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(diplomaSession);

        if (!matcher.matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("diplomaSession = %s is not valid format(year-year+1),ex 2024-2025",diplomaSession));
        }

        // Extract years
        int startYear = Integer.parseInt(matcher.group(1));
        int endYear = Integer.parseInt(matcher.group(2));

        // Check if end year is exactly one year greater than start year
        if (endYear != startYear + 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("diplomaSession = %s is not valid format(year-year+1),ex 2024-2025",diplomaSession));
        }

    }


    public static String localTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }
}
