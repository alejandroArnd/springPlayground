package com.apispring.apispring.infrastructure.mapper;

import com.apispring.apispring.domain.model.BookModel;
import com.apispring.apispring.infrastructure.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookModel toModel(Book book);

    Book toEntity(BookModel bookModel);

    List<BookModel> toListModel(List<Book> bookList);
}
