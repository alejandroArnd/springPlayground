package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.dto.CreateBookRequestDto;
import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.exception.BookAlreadyExist;
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
public class CreateBookTests {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    CreateBook createBook;

    @Test
    public void createBookWhenTitleExists(){
        CreateBookRequestDto createBookRequestDto = new CreateBookRequestDto();
        createBookRequestDto.setTitle("Example");

        when(this.bookRepository.findByTitle(createBookRequestDto.getTitle())).thenReturn(null);
        doNothing().when(this.bookRepository).save(any(BookModel.class));
        this.createBook.handle(createBookRequestDto);

        verify(this.bookRepository, times(1)).findByTitle("Example");
        verify(this.bookRepository, times(1)).save(any(BookModel.class));
    }

    @Test
    public void createBookWhenTitleDoesntExist(){
        CreateBookRequestDto createBookRequestDto = new CreateBookRequestDto();
        createBookRequestDto.setTitle("Example");

        when(this.bookRepository.findByTitle(createBookRequestDto.getTitle())).thenReturn(new BookModel());

        assertThrows(BookAlreadyExist.class,()->{
            this.createBook.handle(createBookRequestDto);
        });
        verify(this.bookRepository, times(1)).findByTitle("Example");
        verify(this.bookRepository, times(0)).save(any(BookModel.class));

    }
}
