package com.cesararana.exe.pageapp.application.validators;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = URLConstraintValidator.class)
public @interface URLValidator {

    String message() default "url can't be empty";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}