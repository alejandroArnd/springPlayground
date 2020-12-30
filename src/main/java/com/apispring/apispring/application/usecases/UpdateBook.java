package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.dto.RequestUpdateBookDto;
import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.exception.BookAlreadyExist;
import com.apispring.apispring.domain.exception.BookNotFound;
import com.apispring.apispring.domain.model.Book;

import java.util.Optional;

public class UpdateBook {

    private BookRepository bookRepository;

    public UpdateBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book handle(RequestUpdateBookDto requestUpdateBookDto) throws BookAlreadyExist, BookNotFound {
        Optional<Book> bookFound = this.bookRepository.findById(requestUpdateBookDto.getId());

        if (!bookFound.isPresent()){
            throw  new BookNotFound();
        }

        Optional<Book> bookAlreadyExist = this.bookRepository.findByTitle(requestUpdateBookDto.getTitle());

        if (bookAlreadyExist.isPresent()){
            throw new BookAlreadyExist();
        }


        bookFound.get().setAuthor(requestUpdateBookDto.getAuthor());
        bookFound.get().setTitle(requestUpdateBookDto.getTitle());
        bookFound.get().setOverview(requestUpdateBookDto.getOverview());
        bookFound.get().setPages(requestUpdateBookDto.getPages());

        this.bookRepository.save(bookFound.get());
        return bookFound.get();
    }
}
