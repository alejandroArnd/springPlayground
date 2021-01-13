package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.model.BookModel;

import java.util.List;

public class FindAllBooks {

    private BookRepository bookRepository;

    public FindAllBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> handle(){
        return this.bookRepository.findAll();
    }
}
