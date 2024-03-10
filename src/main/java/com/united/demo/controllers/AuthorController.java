package com.united.demo.controllers;

import com.united.demo.domain.Author;
import com.united.demo.domain.dto.AuthorDto;
import com.united.demo.mappers.Mapper;
import com.united.demo.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    private AuthorService authorService;
    private Mapper<Author, AuthorDto> mapper;
    public AuthorController(AuthorService authorService, Mapper<Author, AuthorDto> mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/authors")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        var result = authorService.findAll();
        return new ResponseEntity<>(
                result.stream().map(a -> mapper.mapTo(a)).toList(), HttpStatus.OK);
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        var result = authorService.findById(id);
        return result.map(author -> {
            return new ResponseEntity<>(mapper.mapTo(author), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto author) {
        author.setId(null);
        var result = authorService.save(mapper.mapFrom(author));
        return new ResponseEntity<>(mapper.mapTo(result), HttpStatus.CREATED);
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@Valid @RequestBody AuthorDto author, @PathVariable("id") Long id) {

        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        author.setId(id);
        var result = authorService.save(mapper.mapFrom(author));
        return new ResponseEntity<>(mapper.mapTo(result), HttpStatus.OK);
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(@RequestBody AuthorDto author, @PathVariable("id") Long id) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var result = authorService.partialUpdate(mapper.mapFrom(author), id);
        return new ResponseEntity<>(mapper.mapTo(result), HttpStatus.OK);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // this overrides global exception handler for type DataIntegrityViolationException.class
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handler() {
        return new ResponseEntity<>("You suck!", HttpStatus.CONFLICT);
    }
}
