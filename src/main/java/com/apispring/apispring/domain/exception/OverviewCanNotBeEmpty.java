package com.apispring.apispring.domain.exception;

public class OverviewCanNotBeEmpty extends ApiErrors{
    public OverviewCanNotBeEmpty() {
        super("Overview Can Not Be Empty");
    }

    public OverviewCanNotBeEmpty(String message) {
        super(message);
    }
}
