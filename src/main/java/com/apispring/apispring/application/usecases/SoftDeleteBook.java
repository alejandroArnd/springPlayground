package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.exception.BookNotFound;
import com.apispring.apispring.domain.model.Book;

import java.util.Optional;

public class SoftDeleteBook {

    private BookRepository bookRepository;

    public SoftDeleteBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void handle(Long id) throws BookNotFound {
        Optional<Book> bookFound = this.bookRepository.findById(id);

        if (!bookFound.isPresent()){
            throw  new BookNotFound();
        }
        Book bookEntity = bookFound.get();
        bookEntity.setDeleted(true);
        this.bookRepository.save(bookEntity);
    }
}
