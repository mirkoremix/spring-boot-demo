package com.united.demo.domain.dto;

import com.united.demo.domain.Course;
import com.united.demo.validation.CustomValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    private Long id;

    @CustomValidation
    private String firstName;

    @NotNull
    @NotBlank(message = "Invalid LAST NAME")
    private String lastName;

    @Email
    private String email;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private List<Course> courses;
}
