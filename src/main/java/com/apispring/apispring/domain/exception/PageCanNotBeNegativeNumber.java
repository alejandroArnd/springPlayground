package com.apispring.apispring.domain.exception;

public class PageCanNotBeNegativeNumber extends ApiErrors {
    public PageCanNotBeNegativeNumber() {
        super("Page ca not be negative number");
    }

    public PageCanNotBeNegativeNumber(String message) {
        super(message);
    }
}
