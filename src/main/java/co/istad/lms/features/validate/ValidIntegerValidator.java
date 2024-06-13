package co.istad.lms.features.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidIntegerValidator implements ConstraintValidator<ValidInteger, Integer> {

    private String fieldName;
    private String message;

    @Override
    public void initialize(ValidInteger constraintAnnotation) {
        this.fieldName = constraintAnnotation.field();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Use @NotNull for null checks
        }
        // Example additional validation logic
        if (value < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
