package com.apispring.apispring.infrastructure.repository.database;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.model.BookModel;
import com.apispring.apispring.infrastructure.entity.Book;
import com.apispring.apispring.infrastructure.mapper.BookMapper;
import com.apispring.apispring.infrastructure.repository.springdata.JPABookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBBookRepository implements BookRepository {

    @Autowired
    private JPABookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookModel findById(Integer id) {
        return this.bookMapper.toModel(this.bookRepository.findById(id).orElse(null));
    }

    @Override
    public BookModel findByTitle(String title) {
        return this.bookMapper.toModel(this.bookRepository.findByTitle(title).orElse(null));
    }

    @Override
    public List<BookModel> findAll() {
        return this.bookMapper.toListModel(this.bookRepository.findAll());
   }

    @Override
    public void save(BookModel book) {
        this.bookRepository.save(this.bookMapper.toEntity(book));
    }
}
