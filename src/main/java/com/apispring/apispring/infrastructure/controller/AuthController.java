package com.apispring.apispring.infrastructure.controller;

import com.apispring.apispring.application.dto.CreateUserDto;
import com.apispring.apispring.application.dto.LoginRequestDto;
import com.apispring.apispring.application.usecases.CreateUser;
import com.apispring.apispring.infrastructure.Security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final CreateUser createUser;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping(value ="/api/register")
    public ResponseEntity<Object> register(@RequestBody CreateUserDto createUserDto) {
        Map<String, String> json = new HashMap<>();
        json.put("message","User created!");
        this.createUser.handle(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequestDto){
        Authentication authentication =
                this.authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = this.jwtService.createToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
