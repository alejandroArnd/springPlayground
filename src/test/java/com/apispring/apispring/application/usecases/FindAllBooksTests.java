package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.repository.BookRepository;
import com.apispring.apispring.domain.model.BookModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FindAllBooksTests {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    FindAllBooks findAllBooks;

    @Test
    public void findAllBooksTest(){
       List<BookModel> expectedBookModelList = new ArrayList<>();
       expectedBookModelList.add(mock(BookModel.class));
       expectedBookModelList.add(mock(BookModel.class));

       when(this.bookRepository.findAll()).thenReturn(expectedBookModelList);
       List<BookModel> bookModelList = this.findAllBooks.handle();

       verify(this.bookRepository, times(1)).findAll();
       assertEquals(expectedBookModelList, bookModelList);
    }
}
