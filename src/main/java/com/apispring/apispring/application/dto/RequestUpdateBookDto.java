package com.apispring.apispring.application.dto;

public class RequestUpdateBookDto extends CreateBookRequestDto {

    private Integer id;

    public RequestUpdateBookDto(){ }

    public RequestUpdateBookDto(Integer id, String title, String author, String overview, Integer pages) {
        super(title, author, overview, pages);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
