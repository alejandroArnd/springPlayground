package com.apispring.apispring.infrastructure.repository.springdata;

import com.apispring.apispring.infrastructure.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPABookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
