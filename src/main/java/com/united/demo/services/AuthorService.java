package com.united.demo.services;

import com.united.demo.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    boolean isExists(Long id);

    Author save(Author author);

    Author partialUpdate(Author author, Long id);

    void delete(Long id);
}
