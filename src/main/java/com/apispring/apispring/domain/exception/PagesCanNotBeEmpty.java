package com.apispring.apispring.domain.exception;

public class PagesCanNotBeEmpty extends ApiErrors{
    public PagesCanNotBeEmpty() {
        super("Pages can not be empty");
    }

    public PagesCanNotBeEmpty(String message) {
        super(message);
    }
}
