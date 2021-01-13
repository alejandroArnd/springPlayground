package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.dto.RequestUpdateBookDto;
import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.exception.BookAlreadyExist;
import com.apispring.apispring.domain.exception.BookNotFound;
import com.apispring.apispring.domain.model.BookModel;

public class UpdateBook {

    private BookRepository bookRepository;

    public UpdateBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel handle(RequestUpdateBookDto requestUpdateBookDto) throws BookAlreadyExist, BookNotFound {
        BookModel bookFound = this.bookRepository.findById(requestUpdateBookDto.getId());

        if (bookFound == null){
            throw new BookNotFound();
        }

        BookModel bookAlreadyExist = this.bookRepository.findByTitle(requestUpdateBookDto.getTitle());

        if (bookAlreadyExist != null){
            throw new BookAlreadyExist();
        }

        bookFound.setAuthor(requestUpdateBookDto.getAuthor());
        bookFound.setTitle(requestUpdateBookDto.getTitle());
        bookFound.setOverview(requestUpdateBookDto.getOverview());
        bookFound.setPages(requestUpdateBookDto.getPages());

        this.bookRepository.save(bookFound);
        return bookFound;
    }
}
