package com.apispring.apispring.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController {

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public ResponseEntity<String> index() {
        return ResponseEntity.ok().body("Hello World");
    }
}