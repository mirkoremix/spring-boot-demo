package com.united.demo.controllers;

import com.united.demo.models.Author;
import com.united.demo.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path = "/authors")
    public ResponseEntity<List<Author>> getAuthors() {
        var result = authorService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) {
        var result = authorService.findById(id);
        return result.map(author -> {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        var result = authorService.save(author);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<Author> fullUpdateAuthor(@RequestBody Author author, @PathVariable("id") Long id) {

        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        author.setId(id);
        var result = authorService.save(author);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<Author> partialUpdateAuthor(@RequestBody Author author, @PathVariable("id") Long id) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var result = authorService.partialUpdate(author, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
