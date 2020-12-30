package com.apispring.apispring.application.services;

import com.apispring.apispring.application.dto.BookDto;
import com.apispring.apispring.domain.exception.*;

public class ServiceValidatorBooks {

    public void checkPropertiesBook(BookDto requestBook)
            throws
            PagesCanNotBeEmpty,
            PageCanNotBeNegativeNumber,
            AuthorCanNotBeEmpty,
            OverviewCanNotBeEmpty,
            OverviewCanNotHaveLengthHigherOneThousand
    {
        if(requestBook.getPages() == null){
            throw new PagesCanNotBeEmpty();
        }
        if(requestBook.getPages() <= 0){
            throw new PageCanNotBeNegativeNumber();
        }
        if (requestBook.getAuthor().isEmpty()){
            throw new AuthorCanNotBeEmpty();
        }
        if (requestBook.getOverview() == null || requestBook.getOverview().isEmpty()){
            throw new OverviewCanNotBeEmpty();
        }
        if (requestBook.getOverview().length() > 1000){
            throw new OverviewCanNotHaveLengthHigherOneThousand();
        }
    }
}
