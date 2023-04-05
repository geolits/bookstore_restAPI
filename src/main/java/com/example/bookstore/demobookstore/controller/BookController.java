package com.example.bookstore.demobookstore.controller;

import com.example.bookstore.demobookstore.dtos.BookSearchDto;
import com.example.bookstore.demobookstore.entity.Book;
import com.example.bookstore.demobookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping("/insert")
    public ResponseEntity<Book> insertBook(@Valid @RequestBody Book book) {

        var newBook = bookService.insertBook(book);
        return ResponseEntity.ok(newBook);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {

      var optionalBook = bookService.deleteBookByID(id);

        if (optionalBook.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id %d not found", id));
        }
        else {
            var deletedBook = optionalBook.get();
            return ResponseEntity.ok("Book has been deleted");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBookByID(id, book));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterBook(@RequestBody BookSearchDto bookSearchDto) {



        return ResponseEntity.ok(bookService.filterBooks(bookSearchDto));
    }
}
