package co.istad.lms.features.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// BooleanValidator
public class BooleanValidator implements ConstraintValidator<ValidBoolean, Boolean> {

    @Override
    public void initialize(ValidBoolean constraintAnnotation) {
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        // If value is null, it's considered invalid. Use @NotNull for null check.
        return value != null;
    }
}