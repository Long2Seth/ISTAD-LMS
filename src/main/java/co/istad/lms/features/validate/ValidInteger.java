package co.istad.lms.features.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidIntegerValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInteger {
    String message() default "Invalid integer value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String field();
}
