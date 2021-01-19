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
public class FindBookByIdTests {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    FindBookById findBookById;

    @Test
    public void findBookByIdWhenIdExists(){
        BookModel bookModelExpected = new BookModel();
        bookModelExpected.setId(1);

        when(this.bookRepository.findById(1)).thenReturn(bookModelExpected);
        BookModel bookModel = this.findBookById.handle(1);

        verify(this.bookRepository, times(1)).findById(1);
        assertEquals(bookModelExpected,bookModel);

    }

    @Test
    public void findBookByIdWhenIdDoesntExist(){
        when(this.bookRepository.findById(2)).thenReturn(null);

        assertThrows(BookNotFound.class, ()->{
            this.findBookById.handle(2);
        });
        verify(this.bookRepository, times(1)).findById(2);
    }
}
