package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.model.Book;

import java.util.List;

public class FindAllBooks {

    private BookRepository bookRepository;

    public FindAllBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> handle(){
        return this.bookRepository.findAll();
    }
}
