package com.apispring.apispring.domain.model;

public class BookModel {
    private Long id;
    private String title;
    private String author;
    private String overview;
    private Integer pages;
    private Boolean isDeleted = false;

    public BookModel() { }

    public BookModel(String title, String author, String overview, Integer pages) {
        this.title = title;
        this.author = author;
        this.overview = overview;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
