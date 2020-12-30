package com.apispring.apispring.domain.exception;

public class AuthorCanNotBeEmpty extends ApiErrors{
    public AuthorCanNotBeEmpty() {
        super("Field Author Can Not Be Empty");
    }

    public AuthorCanNotBeEmpty(String message) {
        super(message);
    }
}
