package co.istad.lms.features.validate;

import co.istad.lms.features.validate.BooleanValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// ValidBoolean Annotation
@Constraint(validatedBy = BooleanValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBoolean {
    String message() default "Invalid boolean value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
