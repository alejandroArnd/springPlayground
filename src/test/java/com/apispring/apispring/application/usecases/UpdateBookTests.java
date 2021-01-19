package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.dto.RequestUpdateBookDto;
import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.exception.BookAlreadyExist;
import com.apispring.apispring.domain.exception.BookNotFound;
import com.apispring.apispring.domain.model.BookModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateBookTests {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    UpdateBook updateBook;

    @Test
    public void updateBookWhenIdExistsAndTitleDoesntExist(){
        RequestUpdateBookDto requestUpdateBookDto = new RequestUpdateBookDto();
        requestUpdateBookDto.setId(1);
        requestUpdateBookDto.setTitle("Example");

        when(this.bookRepository.findById(requestUpdateBookDto.getId())).thenReturn(new BookModel());
        when(this.bookRepository.findByTitle("Example")).thenReturn(null);
        doNothing().when(this.bookRepository).save(any(BookModel.class));

        BookModel bookModel = this.updateBook.handle(requestUpdateBookDto);

        verify(this.bookRepository, times(1)).findById(1);
        verify(this.bookRepository, times(1)).findByTitle("Example");
        verify(this.bookRepository, times(1)).save(any(BookModel.class));

        assertEquals(requestUpdateBookDto.getTitle(),bookModel.getTitle());
    }

    @Test
    public void updateBookWhenIdDoesntExist(){
        RequestUpdateBookDto requestUpdateBookDto = new RequestUpdateBookDto();
        requestUpdateBookDto.setId(2);

        when(this.bookRepository.findById(requestUpdateBookDto.getId())).thenReturn(null);

        assertThrows(BookNotFound.class, ()->{
            this.updateBook.handle(requestUpdateBookDto);
        });
        verify(this.bookRepository, times(1)).findById(2);
        verify(this.bookRepository, times(0)).findByTitle("Example");
        verify(this.bookRepository, times(0)).save(any(BookModel.class));
    }

    @Test
    public void updateBookWhenTitleAlreadyExist(){
        RequestUpdateBookDto requestUpdateBookDto = new RequestUpdateBookDto();
        requestUpdateBookDto.setId(2);
        requestUpdateBookDto.setTitle("Example");

        when(this.bookRepository.findById(requestUpdateBookDto.getId())).thenReturn(new BookModel());
        when(this.bookRepository.findByTitle("Example")).thenReturn(new BookModel());

        assertThrows(BookAlreadyExist.class, ()->{
            this.updateBook.handle(requestUpdateBookDto);
        });
        verify(this.bookRepository, times(1)).findById(2);
        verify(this.bookRepository, times(1)).findByTitle("Example");
        verify(this.bookRepository, times(0)).save(any(BookModel.class));
    }
}
