package com.apispring.apispring.infrastructure.mapper;

import com.apispring.apispring.domain.model.BookModel;
import com.apispring.apispring.domain.model.UserModel;
import com.apispring.apispring.infrastructure.entity.Book;
import com.apispring.apispring.infrastructure.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel toModel(User user);

    User toEntity(UserModel userModel);
}
