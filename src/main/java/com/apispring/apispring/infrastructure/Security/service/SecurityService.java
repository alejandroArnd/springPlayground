package com.apispring.apispring.infrastructure.Security.service;

import com.apispring.apispring.application.repository.UserRepository;
import com.apispring.apispring.application.usecases.FindUserById;
import com.apispring.apispring.application.usecases.FindUserByUsername;
import com.apispring.apispring.domain.model.UserModel;
import com.apispring.apispring.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService implements UserDetailsService {

    private final FindUserById findUserById;
    private final FindUserByUsername findUserByUsername;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userMapper.toEntity(this.findUserByUsername.handle(username));
    }

    public UserModel loadUserById(int id){
        return this.findUserById.handle(id);
    }
}
