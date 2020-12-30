package com.apispring.apispring.domain.exception;

public class BookNotFound extends ApiErrors{
    public BookNotFound() {
        super("Book Not Found");
    }
    public BookNotFound(String message) {
        super(message);
    }
}
