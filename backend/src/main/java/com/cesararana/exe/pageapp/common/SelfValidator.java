package com.cesararana.exe.pageapp.common;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

public class SelfValidator<T extends  Object> {

    private final Validator validator;

    public SelfValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void validate() {
        var errors = validator.validate(this);
        if (!errors.isEmpty()) {
            throw new ConstraintViolationException(errors);
        }
    }

}
