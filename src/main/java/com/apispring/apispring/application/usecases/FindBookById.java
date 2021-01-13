package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.exception.BookNotFound;
import com.apispring.apispring.domain.model.BookModel;

public class FindBookById {

    private BookRepository bookRepository;

    public FindBookById(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel handle(Long id){
        BookModel book = this.bookRepository.findById(id);

        if (book == null){
            throw new BookNotFound();
        }

        return book;
    }
}
