package com.apispring.apispring.infrastructure.repository.database;

import com.apispring.apispring.application.repository.UserRepository;
import com.apispring.apispring.domain.model.UserModel;
import com.apispring.apispring.infrastructure.entity.User;
import com.apispring.apispring.infrastructure.mapper.UserMapper;
import com.apispring.apispring.infrastructure.repository.springdata.JPAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DBUserRepository implements UserRepository {

    @Autowired
    private JPAUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserModel findByUsername(String username) {
        return this.userMapper.toModel(this.userRepository.findByUsername(username).orElse(null));
    }

    @Override
    public UserModel findById(Integer id) {
        return this.userMapper.toModel(this.userRepository.findById(id).orElse(null));
    }

    @Override
    public void save(UserModel userModel) {
        this.userRepository.save(this.userMapper.toEntity(userModel));
    }
}
