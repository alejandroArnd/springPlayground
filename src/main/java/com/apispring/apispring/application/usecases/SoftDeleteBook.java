package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.exception.BookNotFound;
import com.apispring.apispring.domain.model.BookModel;

public class SoftDeleteBook {

    private BookRepository bookRepository;

    public SoftDeleteBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel handle(Long id){
        BookModel bookFound = this.bookRepository.findById(id);

        if (bookFound == null){
            throw new BookNotFound();
        }

        BookModel book = bookFound;
        book.setDeleted(true);
        this.bookRepository.save(book);
        return book;
    }
}
