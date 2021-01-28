package com.apispring.apispring.application.usecases;

import com.apispring.apispring.application.dto.CreateUserDto;
import com.apispring.apispring.application.repository.UserRepository;
import com.apispring.apispring.domain.model.UserModel;
import com.apispring.apispring.domain.model.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateUser {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void handle (CreateUserDto createUserDto){
        String passwordCode = this.passwordEncoder.encode(createUserDto.getPassword());
        Set<UserRole> userRoles = Stream.of(UserRole.USER_ROLE).collect(Collectors.toSet());
        UserModel userModel = new UserModel(createUserDto.getUsername(),createUserDto.getEmail(),passwordCode,userRoles);
        this.userRepository.save(userModel);
    }
}
