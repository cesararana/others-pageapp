package com.cesararana.exe.pageapp.application.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.URL;

public class URLConstraintValidator implements ConstraintValidator<URLValidator, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            new URL(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
