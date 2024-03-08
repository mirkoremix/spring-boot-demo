package com.united.demo.models;

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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    private String title;

    private String description;

    private LocalDate createdAt;

    private LocalDate updatedAt;

//    @ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "author_course",
            joinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "id")
            }
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "course")
    private List<Section> sections;

}