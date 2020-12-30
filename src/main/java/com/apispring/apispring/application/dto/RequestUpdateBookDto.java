package com.apispring.apispring.application.dto;

public class RequestUpdateBookDto extends BookDto {

    private Long id;

    public RequestUpdateBookDto(Long id, String title, String author, String overview, Integer pages) {
        super(title, author, overview, pages);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
