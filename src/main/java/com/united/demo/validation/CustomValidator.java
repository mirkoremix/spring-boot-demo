package com.united.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<CustomValidation, String> {
    @Override
    public void initialize(CustomValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String firstName, ConstraintValidatorContext context) {
        return Character.isUpperCase(firstName.charAt(0));
    }
}
