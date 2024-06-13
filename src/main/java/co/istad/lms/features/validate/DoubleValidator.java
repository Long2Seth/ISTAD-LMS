package co.istad.lms.features.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// DoubleValidator
public class DoubleValidator implements ConstraintValidator<ValidDouble, Double> {

    @Override
    public void initialize(ValidDouble constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        // Implement your custom double validation logic here
        // For example, return true if value is not null and within a specific range
        return value != null && value >= 0.0;
    }
}