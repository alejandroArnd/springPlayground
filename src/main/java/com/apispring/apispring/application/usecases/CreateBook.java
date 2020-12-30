package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.application.dto.BookDto;
import com.apispring.apispring.domain.exception.*;
import com.apispring.apispring.domain.model.Book;

import java.util.Optional;

public class CreateBook {

    private BookRepository bookRepository;

    public CreateBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void handle (BookDto bookDto) throws BookAlreadyExist {
        Optional<Book> bookFound = this.bookRepository.findByTitle(bookDto.getTitle());

        if (bookFound.isPresent()){
            throw new BookAlreadyExist();
        }
        Book book= new Book(bookDto.getTitle(),bookDto.getAuthor(), bookDto.getOverview(), bookDto.getPages());
        this.bookRepository.save(book);
    }
}
