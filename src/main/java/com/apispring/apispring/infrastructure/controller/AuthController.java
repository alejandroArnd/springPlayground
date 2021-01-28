package com.apispring.apispring.infrastructure.controller;

import com.apispring.apispring.application.dto.CreateUserDto;
import com.apispring.apispring.application.usecases.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final CreateUser createUser;

    @PostMapping(value ="/api/register")
    public ResponseEntity<Object> register(@RequestBody CreateUserDto createUserDto) {
        Map<String, String> json = new HashMap<>();
        json.put("message","User created!");
        this.createUser.handle(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }
}
