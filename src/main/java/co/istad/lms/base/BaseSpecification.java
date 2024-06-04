package co.istad.lms.base;

import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class BaseSpecification<T> {

    // Method to filter data based on specified criteria
    public Specification<T> filter(FilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            for (SpecsDto specs : filterDto.specsDto) {
                try {
                    // Validate if the column exists in the entity class
                    if (!isValidColumn(root.getJavaType(), specs.getColumn())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid column: " + specs.getColumn());
                    }

                    // Handle join table if specified
                    if (specs.getJoinTable() != null) {

                        Join<Object, Object> join = getJoin(root, specs.getJoinTable());

                        if (!isValidColumn(join.getJavaType(), specs.getColumn())) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid column: " + specs.getColumn());
                        }

                        // Create predicate for the join table
                        predicates.add(createPredicate(join, criteriaBuilder, specs));
                    } else {
                        // Create predicate for the root table
                        predicates.add(createPredicate(root, criteriaBuilder, specs));
                    }
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "filter has been not found, check operator and other information");
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An error occurred while processing the request");
                }
            }
            // Combine predicates based on the global operator (AND/OR)
            return filterDto.globalOperator == FilterDto.GlobalOperator.AND
                    ? criteriaBuilder.and(predicates.toArray(new Predicate[0]))
                    : criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    // Method to check if a column exists in the entity class
    private boolean isValidColumn(Class<?> entityClass, String column) {

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.getName().equals(column)) {
                return true;
            }
        }
        return false;
    }

    // Method to create predicate based on specified criteria
    private Predicate createPredicate(From<?, ?> from, CriteriaBuilder criteriaBuilder, SpecsDto specs) {

        try {
            // Validate if the operation is valid
            if (!isValidOperation(specs.getOperation())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Invalid operation: " + specs.getOperation() + ". Accepted values: " + Arrays.toString(SpecsDto.Operation.values()));
            }

            // Get the field type of the specified column
            Field field = from.getJavaType().getDeclaredField(specs.getColumn());
            Class<?> fieldType = field.getType();

            // Switch case to handle different operations based on the field type
            switch (specs.getOperation()) {
                case EQUAL:
                    return createEqualPredicate(from, criteriaBuilder, specs, fieldType);

                case LIKE:
                    if (fieldType == String.class) {
                        return criteriaBuilder.like(criteriaBuilder.lower(from.get(specs.getColumn())), "%" + specs.getValue().toLowerCase() + "%");

                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "LIKE operation is not supported for column type: " + fieldType.getSimpleName());
                    }

                case IN:
                    return createInPredicate(from, criteriaBuilder, specs, fieldType);

                case GREATER_THAN:
                    return createGreaterThanPredicate(from, criteriaBuilder, specs, fieldType);

                case LESS_THAN:
                    return createLessThanPredicate(from, criteriaBuilder, specs, fieldType);

                case BETWEEN:
                    return createBetweenPredicate(from, criteriaBuilder, specs, fieldType);

                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported operation: " + specs.getOperation());
            }
        } catch (NoSuchFieldException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid column: " + specs.getColumn());
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for numeric column: " + specs.getValue());
        }
    }

    // Method to validate if the operation is part of the allowed operations
    private boolean isValidOperation(SpecsDto.Operation operation) {

        for (SpecsDto.Operation op : SpecsDto.Operation.values()) {
            if (op == operation) {
                return true;
            }
        }
        return false;
    }

    // Method to create an EQUAL predicate based on the field type
    private Predicate createEqualPredicate(From<?, ?> from, CriteriaBuilder criteriaBuilder, SpecsDto specs, Class<?> fieldType) {

        if (fieldType == String.class) {
            return criteriaBuilder.equal(from.get(specs.getColumn()), specs.getValue());

        } else if (fieldType == Integer.class || fieldType == int.class) {
            return criteriaBuilder.equal(from.get(specs.getColumn()), Integer.parseInt(specs.getValue()));

        } else if (fieldType == Double.class || fieldType == double.class) {
            return criteriaBuilder.equal(from.get(specs.getColumn()), Double.parseDouble(specs.getValue()));

        } else if (fieldType == Boolean.class || fieldType == boolean.class) {

            if (!"true".equalsIgnoreCase(specs.getValue()) && !"false".equalsIgnoreCase(specs.getValue())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for boolean column: " + specs.getValue());
            }

            return criteriaBuilder.equal(from.get(specs.getColumn()), Boolean.parseBoolean(specs.getValue()));

        } else if (fieldType == LocalDate.class) {
            return criteriaBuilder.equal(from.get(specs.getColumn()), LocalDate.parse(specs.getValue()));

        } else if (fieldType == LocalTime.class) {
            return criteriaBuilder.equal(from.get(specs.getColumn()), LocalTime.parse(specs.getValue()));

        } else if (fieldType == LocalDateTime.class) {
            return criteriaBuilder.equal(from.get(specs.getColumn()), LocalDateTime.parse(specs.getValue()));

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported operation for column type: " + fieldType.getSimpleName());
        }
    }

    // Method to create an IN predicate based on the field type
    private Predicate createInPredicate(From<?, ?> from, CriteriaBuilder criteriaBuilder, SpecsDto specs, Class<?> fieldType) {

        if (fieldType == String.class) {
            return from.get(specs.getColumn()).in(Arrays.asList(specs.getValue().split(",")));

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IN operation is not supported for column type: " + fieldType.getSimpleName());
        }
    }

    // Method to create a GREATER_THAN predicate based on the field type
    private Predicate createGreaterThanPredicate(From<?, ?> from, CriteriaBuilder criteriaBuilder, SpecsDto specs, Class<?> fieldType) {

        if (fieldType == Integer.class || fieldType == int.class) {
            return criteriaBuilder.greaterThan(from.get(specs.getColumn()), Integer.parseInt(specs.getValue()));

        } else if (fieldType == Double.class || fieldType == double.class) {
            return criteriaBuilder.greaterThan(from.get(specs.getColumn()), Double.parseDouble(specs.getValue()));

        } else if (fieldType == LocalDate.class) {
            return criteriaBuilder.greaterThan(from.get(specs.getColumn()), LocalDate.parse(specs.getValue()));

        } else if (fieldType == LocalTime.class) {
            return criteriaBuilder.greaterThan(from.get(specs.getColumn()), LocalTime.parse(specs.getValue()));

        } else if (fieldType == LocalDateTime.class) {
            return criteriaBuilder.greaterThan(from.get(specs.getColumn()), LocalDateTime.parse(specs.getValue()));

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "GREATER_THAN operation is not supported for column type: " + fieldType.getSimpleName());
        }
    }

    // Method to create a LESS_THAN predicate based on the field type
    private Predicate createLessThanPredicate(From<?, ?> from, CriteriaBuilder criteriaBuilder, SpecsDto specs, Class<?> fieldType) {

        if (fieldType == Integer.class || fieldType == int.class) {
            return criteriaBuilder.lessThan(from.get(specs.getColumn()), Integer.parseInt(specs.getValue()));

        } else if (fieldType == Double.class || fieldType == double.class) {
            return criteriaBuilder.lessThan(from.get(specs.getColumn()), Double.parseDouble(specs.getValue()));

        } else if (fieldType == LocalDate.class) {
            return criteriaBuilder.lessThan(from.get(specs.getColumn()), LocalDate.parse(specs.getValue()));

        } else if (fieldType == LocalTime.class) {
            return criteriaBuilder.lessThan(from.get(specs.getColumn()), LocalTime.parse(specs.getValue()));

        } else if (fieldType == LocalDateTime.class) {
            return criteriaBuilder.lessThan(from.get(specs.getColumn()), LocalDateTime.parse(specs.getValue()));

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "LESS_THAN operation is not supported for column type: " + fieldType.getSimpleName());
        }
    }

    // Method to create a BETWEEN predicate based on the field type
    private Predicate createBetweenPredicate(From<?, ?> from, CriteriaBuilder criteriaBuilder, SpecsDto specs, Class<?> fieldType) {

        String[] split = specs.getValue().split(",");
        if (fieldType == Integer.class || fieldType == int.class) {
            return criteriaBuilder.between(from.get(specs.getColumn()), Integer.parseInt(split[0]), Integer.parseInt(split[1]));

        } else if (fieldType == Double.class || fieldType == double.class) {
            return criteriaBuilder.between(from.get(specs.getColumn()), Double.parseDouble(split[0]), Double.parseDouble(split[1]));

        } else if (fieldType == LocalDate.class) {
            return criteriaBuilder.between(from.get(specs.getColumn()), LocalDate.parse(split[0]), LocalDate.parse(split[1]));

        } else if (fieldType == LocalTime.class) {
            return criteriaBuilder.between(from.get(specs.getColumn()), LocalTime.parse(split[0]), LocalTime.parse(split[1]));

        } else if (fieldType == LocalDateTime.class) {
            return criteriaBuilder.between(from.get(specs.getColumn()), LocalDateTime.parse(split[0]), LocalDateTime.parse(split[1]));

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BETWEEN operation is not supported for column type: " + fieldType.getSimpleName());
        }
    }

    // Method to create a join between tables based on the join table path
    private Join<Object, Object> getJoin(From<?, ?> root, String joinTable) {

        try {
            String[] joinTableSplit = joinTable.split("\\.");

            Join<Object, Object> join = root.join(joinTableSplit[0]);

            for (int i = 1; i < joinTableSplit.length; i++) {
                join = join.join(joinTableSplit[i]);
            }

            return join;

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid join table: " + joinTable);
        }
    }

    // Method to parse comma-separated integers into a list
    private List<Integer> parseIntegers(String value) {

        List<Integer> result = new ArrayList<>();
        for (String val : value.split(",")) {
            result.add(Integer.parseInt(val));
        }
        return result;
    }

    // Method to parse comma-separated doubles into a list
    private List<Double> parseDoubles(String value) {

        List<Double> result = new ArrayList<>();

        for (String val : value.split(",")) {
            result.add(Double.parseDouble(val));
        }
        return result;
    }

    // Method to parse comma-separated LocalDates into a list
    private List<LocalDate> parseLocalDates(String value) {

        List<LocalDate> result = new ArrayList<>();

        for (String val : value.split(",")) {
            result.add(LocalDate.parse(val));
        }
        return result;
    }

    // Method to parse comma-separated LocalTimes into a list
    private List<LocalTime> parseLocalTimes(String value) {

        List<LocalTime> result = new ArrayList<>();

        for (String val : value.split(",")) {
            result.add(LocalTime.parse(val));
        }
        return result;
    }

    // Method to parse comma-separated LocalDateTimes into a list
    private List<LocalDateTime> parseLocalDateTimes(String value) {

        List<LocalDateTime> result = new ArrayList<>();

        for (String val : value.split(",")) {
            result.add(LocalDateTime.parse(val));
        }
        return result;
    }

    // DTO class for specifying filtering criteria
    @Getter
    @Setter
    public static class SpecsDto {

        private String column;
        private String value;
        private String joinTable;
        private Operation operation;

        // Enum for supported operations
        public enum Operation {
            EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN;
        }
    }

    // DTO class for specifying the overall filter configuration
    @Getter
    @Setter
    public static class FilterDto {

        private List<SpecsDto> specsDto;
        private GlobalOperator globalOperator;

        // Enum for global operators (AND/OR)
        public enum GlobalOperator {
            AND, OR;
        }
    }
}
