package com.united.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CustomValidator.class)
public @interface CustomValidation {

    String message() default "invalid first character";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
