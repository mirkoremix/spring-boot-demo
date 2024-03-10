package com.united.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    //    @ManyToMany(mappedBy = "authors", cascade = CascadeType.DETACH)
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Course> courses;
}
