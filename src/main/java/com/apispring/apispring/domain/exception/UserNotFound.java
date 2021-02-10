package com.apispring.apispring.domain.exception;

public class UserNotFound extends ApiErrors{
    public UserNotFound(String message) {
        super(message);
    }

    public UserNotFound() {
        super("User not found");
    }
}
