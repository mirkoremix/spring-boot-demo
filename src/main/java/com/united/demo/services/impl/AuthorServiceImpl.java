package com.united.demo.services.impl;

import com.united.demo.domain.Author;
import com.united.demo.repositories.AuthorRepository;
import com.united.demo.services.AuthorService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return StreamSupport.stream(authorRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author partialUpdate(Author author, Long id) {
        author.setId(id);
        return authorRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(author.getFirstName()).ifPresent(existingAuthor::setFirstName);
            Optional.ofNullable(author.getLastName()).ifPresent(existingAuthor::setLastName);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author does not exist"));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
