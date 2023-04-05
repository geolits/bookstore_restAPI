package com.example.bookstore.demobookstore.service;

import com.example.bookstore.demobookstore.dtos.BookSearchDto;
import com.example.bookstore.demobookstore.entity.Book;
import com.example.bookstore.demobookstore.exception.AuthorNotExistException;
import com.example.bookstore.demobookstore.exception.MyConstraintViolationException;
import com.example.bookstore.demobookstore.repository.AuthorRepository;
import com.example.bookstore.demobookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final AuthorService authorService;

   public List<Book> getBooks() {
       var listOfBooks = bookRepository.findAll();
       return listOfBooks;
   }

    public Book insertBook(Book book) {

       var authorsListOptional = Optional.ofNullable(book.getAuthors());
        authorsListOptional.ifPresent( list ->
                list.stream().forEach(a ->
                        authorRepository.findById(a.getId())
                                .ifPresentOrElse(
                                        (value)
                                                -> { },
                                        ()
                                                -> { throw new AuthorNotExistException("Authors id: " + a.getId() + ", doesn't exist");})));

        try{
            var newBook =  bookRepository.save(book);
            return newBook;
        }
        catch( DataIntegrityViolationException e){
            throw new MyConstraintViolationException("Constraint violation: Book Title & Volume already exist");
        }

    }

    public Optional<Book> deleteBookByID(Long id) {

        var optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        }

        return optionalBook;
    }

    public Book updateBookByID(Long id, Book book) {

        var optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id %d not found", id));
        }

        var listOfAuthors = book.getAuthors();

        //I just check if the authoId is in the DB
       listOfAuthors.stream().forEach( author -> authorService.getAuthorById(author.getId()));

        var updatedBook = optionalBook.get();

        updatedBook.setTitle(book.getTitle());
        updatedBook.setDescription(book.getDescription());
        updatedBook.setPublicationDate(book.getPublicationDate());
        updatedBook.setAuthors(book.getAuthors());
        updatedBook.setBookType(book.getBookType());
        updatedBook.setGenre(book.getGenre());
        updatedBook.setPrice(book.getPrice());
        updatedBook.setQuantity(book.getQuantity());
        updatedBook.setVolume(book.getVolume());

        updatedBook = bookRepository.save(updatedBook);

        return updatedBook;
    }

    public List<Book> filterBooks(BookSearchDto bookSearchDto) {

       List<Book> allFilteredBooks = bookRepository.findAll();

       if(bookSearchDto.getBookGenresList().size() > 0 ) {
           allFilteredBooks =  allFilteredBooks.stream()
                   .filter( book ->
                           bookSearchDto.getBookGenresList().contains(book.getGenre().name()))
                   .collect(Collectors.toList());
       }

       if(bookSearchDto.getBookTypesList().size() > 0) {
           allFilteredBooks =  allFilteredBooks.stream()
                   .filter( book ->
                           bookSearchDto.getBookTypesList().contains(book.getBookType().name()))
                   .collect(Collectors.toList());
       }

       if(bookSearchDto.getAuthorNamesList().size() > 0) {

           allFilteredBooks = allFilteredBooks.stream()
                   .filter( book -> book.getAuthors().stream()
                               .filter(author ->
                                       bookSearchDto.getAuthorNamesList().contains(author.getName()))
                               .findAny().isPresent())
                   .collect(Collectors.toList());
       }

       if(bookSearchDto.getMinNumberOfVolumes() != null ) {
           var groupedBooksByTitle = allFilteredBooks.stream()
                   .collect(Collectors.groupingBy(Book::getTitle,Collectors.toList()));
          allFilteredBooks = groupedBooksByTitle.values().stream()
                  .filter(b -> b.size() >= bookSearchDto.getMinNumberOfVolumes())
                  .flatMap(List::stream)
                  .collect(Collectors.toList());

       }

       return allFilteredBooks;
    }
}