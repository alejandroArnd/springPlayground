package com.apispring.apispring.application.repository;

import com.apispring.apispring.domain.model.BookModel;

import java.util.List;

public interface BookRepository {

    BookModel findById(Long id);

    BookModel findByTitle(String title);

    List<BookModel> findAll();

    void save(BookModel book);
}
