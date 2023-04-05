package com.example.bookstore.demobookstore.service;

import com.example.bookstore.demobookstore.entity.Author;
import com.example.bookstore.demobookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author insertAuthor(Author author) {

        Author newAuthor = new Author();
        newAuthor.setName(author.getName());
        newAuthor.setEmail(author.getEmail());
        authorRepository.save(newAuthor);
        return newAuthor;
    }

    public Author getAuthorById(Long id) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Author id must not be null"));
        }

        var optionalAuthor = authorRepository.findById(id);

        if (optionalAuthor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Author with id %d not found", id));
        }
        return optionalAuthor.get();
    }

}
