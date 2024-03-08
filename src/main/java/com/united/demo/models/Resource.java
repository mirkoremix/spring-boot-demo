package com.united.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "resource")
@Table(name = "resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer size;

    private String name;

    private String url;

    // staviti ovo da bude enum
    // enum sa seterom
    private String type;

    @OneToOne(mappedBy = "resource")
    private Lecture lecture;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
