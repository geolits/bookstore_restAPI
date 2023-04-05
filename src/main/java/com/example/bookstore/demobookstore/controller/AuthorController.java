package com.example.bookstore.demobookstore.controller;

import com.example.bookstore.demobookstore.entity.Author;
import com.example.bookstore.demobookstore.exception.MyConstraintViolationException;
import com.example.bookstore.demobookstore.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/insert")
    public ResponseEntity<Author> insertAuthor(@Valid @RequestBody Author author) {

        try {
            var newAuthor = authorService.insertAuthor(author);
            return ResponseEntity.ok(newAuthor);
        }
        catch(DataIntegrityViolationException e){
            throw new MyConstraintViolationException("Constraint vailotation: email exists");
        }
    }

}
