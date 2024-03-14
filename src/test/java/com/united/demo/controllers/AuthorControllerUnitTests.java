package com.united.demo.controllers;


import com.united.demo.domain.Author;
import com.united.demo.domain.dto.AuthorDto;
import com.united.demo.validation.CustomValidation;
import com.united.demo.validation.CustomValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@ExtendWith(MockitoExtension.class)
public class AuthorControllerUnitTests {

    Validator validator;

    @Test
    public void somethin() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        var a = AuthorDto.builder().id(1L).firstName("maloslovo").build();

//        Set<CustomValidator<> violations = validator.validate(theInstanceOfTheClassYouAreValidating);
//        var violations = validator.validate(a);
        Set<ConstraintViolation<AuthorDto>> violations = validator.validate(a);

        assertFalse(violations.isEmpty());
    }
}
