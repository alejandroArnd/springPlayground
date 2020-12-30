package com.apispring.apispring.application.repository;

import com.apispring.apispring.domain.model.Book;
import com.apispring.apispring.application.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(Long id);

    Optional<Book> findByTitle(String title);

    List<Book> findAll();

    void save(Book book);
}
