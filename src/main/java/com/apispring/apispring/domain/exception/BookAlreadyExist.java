package com.apispring.apispring.domain.exception;

public class BookAlreadyExist extends ApiErrors{

    public BookAlreadyExist() {
        super("This Book Already Exist");
    }

    public BookAlreadyExist(String message) {
        super(message);
    }

}
