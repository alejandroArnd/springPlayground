package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.application.dto.CreateBookRequestDto;
import com.apispring.apispring.domain.exception.*;
import com.apispring.apispring.domain.model.BookModel;

public class CreateBook {

    private BookRepository bookRepository;

    public CreateBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void handle (CreateBookRequestDto createBookRequestDto) throws BookAlreadyExist {
       BookModel bookFound = this.bookRepository.findByTitle(createBookRequestDto.getTitle());

       if (bookFound != null){
           throw new BookAlreadyExist();
       }
       BookModel book= new BookModel(createBookRequestDto.getTitle(), createBookRequestDto.getAuthor(), createBookRequestDto.getOverview(), createBookRequestDto.getPages());
       this.bookRepository.save(book);
    }
}
