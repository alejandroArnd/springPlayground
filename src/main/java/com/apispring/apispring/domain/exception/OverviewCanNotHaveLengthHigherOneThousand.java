package com.apispring.apispring.domain.exception;

public class OverviewCanNotHaveLengthHigherOneThousand extends ApiErrors{
    public OverviewCanNotHaveLengthHigherOneThousand() {
        super("Overview can not have length higher one thousand");
    }

    public OverviewCanNotHaveLengthHigherOneThousand(String message) {
        super(message);
    }
}
