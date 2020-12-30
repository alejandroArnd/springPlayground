package com.apispring.apispring.infrastructure.repository.database;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.model.Book;
import com.apispring.apispring.infrastructure.repository.springdata.JPABookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DBBookRepository implements BookRepository {

    @Autowired
    private JPABookRepository bookRepository;

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }
}
