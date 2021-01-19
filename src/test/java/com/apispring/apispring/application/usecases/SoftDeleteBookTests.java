package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
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
public class SoftDeleteBookTests {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    SoftDeleteBook softDeleteBook;

    @Test
    public void softDeleteBookWhenIdExist(){
        BookModel bookModelExpected = new BookModel();
        bookModelExpected.setId(1);

        when(this.bookRepository.findById(1)).thenReturn(bookModelExpected);
        BookModel bookModel = this.softDeleteBook.handle(1);

        verify(this.bookRepository, times(1)).findById(1);
        verify(this.bookRepository, times(1)).save(bookModelExpected);
        assertTrue(bookModel.getDeleted());
    }

    @Test
    public void softDeleteBookWhenIdDoesntExist(){
        when(this.bookRepository.findById(2)).thenReturn(null);

        assertThrows(BookNotFound.class, ()->{
            this.softDeleteBook.handle(2);
        });
        verify(this.bookRepository, times(1)).findById(2);
        verify(this.bookRepository, times(0)).save(any(BookModel.class));
    }
}
