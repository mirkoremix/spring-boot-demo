package com.united.demo.domain.dto;

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
public class CourseDto {

    private Long id;

    private String title;

    private String description;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private List<AuthorDto> authors;
}
