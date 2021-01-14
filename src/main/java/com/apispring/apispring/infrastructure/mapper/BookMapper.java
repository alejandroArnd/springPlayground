package com.apispring.apispring.infrastructure.mapper;

import com.apispring.apispring.domain.model.BookModel;
import com.apispring.apispring.infrastructure.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "isDeleted",target = "deleted")
    BookModel toModel(Book book);

    @Mapping(source = "deleted",target = "isDeleted")
    Book toEntity(BookModel bookModel);

    @Mapping(source = "deleted",target = "isDeleted")
    List<BookModel> toListModel(List<Book> bookList);
}
