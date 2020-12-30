package com.apispring.apispring.infrastructure.controller;

import com.apispring.apispring.application.dto.RequestUpdateBookDto;
import com.apispring.apispring.application.services.ServiceValidatorBooks;
import com.apispring.apispring.application.usecases.*;
import com.apispring.apispring.domain.exception.ApiErrors;
import com.apispring.apispring.application.dto.BookDto;
import com.apispring.apispring.domain.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BooksController {

    private CreateBook createBook;
    private FindAllBooks findAllBooks;
    private FindBookById findBookById;
    private SoftDeleteBook softDeleteBook;
    private UpdateBook updateBook;
    private ServiceValidatorBooks validatorBooks;

    public BooksController(
            CreateBook createBook,
            FindAllBooks findAllBooks,
            FindBookById findBookById,
            SoftDeleteBook softDeleteBook,
            UpdateBook updateBook,
            ServiceValidatorBooks validatorBooks
    ) {
        this.createBook = createBook;
        this.findAllBooks = findAllBooks;
        this.findBookById = findBookById;
        this.softDeleteBook = softDeleteBook;
        this.updateBook = updateBook;
        this.validatorBooks = validatorBooks;
    }

    @PostMapping(value ="/api/books")
    public ResponseEntity<Object> createBook(@RequestBody BookDto bookDto) {
        Map<String, String> json = new HashMap<>();
        try {
            this.validatorBooks.checkPropertiesBook(bookDto);
            this.createBook.handle(bookDto);
            json.put("message", "book created!");
        } catch (ApiErrors apiErrors) {
            apiErrors.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrors.toJson());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(json);

    }


    @GetMapping(value ="/api/books")
    public ResponseEntity<Object> findAllBooks() {
        List allBooks = this.findAllBooks.handle();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @GetMapping(value ="/api/books/{id}")
    public ResponseEntity<Object> findBooksById(@PathVariable("id") Long id) {
        Object book;
        try {
            book = this.findBookById.handle(id);
        } catch (ApiErrors apiErrors) {
            apiErrors.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrors.toJson());
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping(value ="/api/books/{id}")
    public ResponseEntity<Object> softDeleteBook(@PathVariable("id") Long id) {
        Map<String, String> json =new HashMap<>();
        try{
            this.softDeleteBook.handle(id);
            json.put("message", "Delete book");
        } catch (ApiErrors apiErrors) {
            apiErrors.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrors.toJson());
        }
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PutMapping(value ="/api/books")
    public ResponseEntity<Object> updateBook(@RequestBody RequestUpdateBookDto requestUpdateBookDto) {
        Book bookUpdate;
        try{
            this.validatorBooks.checkPropertiesBook(requestUpdateBookDto);
            bookUpdate = this.updateBook.handle(requestUpdateBookDto);
        } catch (ApiErrors apiErrors) {
            apiErrors.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrors.toJson());
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookUpdate);
    }
}