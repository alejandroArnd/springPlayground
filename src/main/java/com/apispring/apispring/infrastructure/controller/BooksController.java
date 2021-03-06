package com.apispring.apispring.infrastructure.controller;

import com.apispring.apispring.application.dto.RequestUpdateBookDto;
import com.apispring.apispring.application.services.ValidatorBooksService;
import com.apispring.apispring.application.usecases.*;
import com.apispring.apispring.domain.exception.ApiErrors;
import com.apispring.apispring.application.dto.CreateBookRequestDto;
import com.apispring.apispring.domain.model.BookModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final CreateBook createBook;
    private final FindAllBooks findAllBooks;
    private final FindBookById findBookById;
    private final SoftDeleteBook softDeleteBook;
    private final UpdateBook updateBook;
    private final ValidatorBooksService validatorBooks;

    @PostMapping(value ="/api/books")
    public ResponseEntity<Object> createBook(@RequestBody CreateBookRequestDto createBookRequestDto) {
        Map<String, String> json = new HashMap<>();
        try {
            this.validatorBooks.checkPropertiesBook(createBookRequestDto);
            this.createBook.handle(createBookRequestDto);
            json.put("message", "book created!");
        } catch (ApiErrors apiErrors) {
            apiErrors.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrors.toJson());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }

    @GetMapping(value ="/api/books")
    public ResponseEntity<List> findAllBooks() {
        List allBooks = this.findAllBooks.handle();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @GetMapping(value ="/api/books/{id}")
    public ResponseEntity<Object> findBooksById(@PathVariable("id") Integer id) {
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
    public ResponseEntity<Object> softDeleteBook(@PathVariable("id") Integer id) {
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
        BookModel bookUpdate;
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