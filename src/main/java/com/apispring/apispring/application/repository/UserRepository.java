package com.apispring.apispring.application.repository;

import com.apispring.apispring.domain.model.UserModel;
import com.apispring.apispring.infrastructure.entity.User;

import java.util.Optional;

public interface UserRepository {

    UserModel findByUsername(String username);

    UserModel findById(Integer id);

    void save(UserModel userModel);
}
