package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.exception.BookNotFound;
import com.apispring.apispring.domain.model.Book;

import java.util.Optional;

public class FindBookById {

    private BookRepository bookRepository;

    public FindBookById(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book handle(Long id) throws BookNotFound {
        Optional<Book> book = this.bookRepository.findById(id);

        if (!book.isPresent()){
            throw  new BookNotFound();
        }
        return book.get();
    }
}
