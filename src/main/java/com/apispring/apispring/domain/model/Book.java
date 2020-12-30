package com.apispring.apispring.domain.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title"})
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    @Column(name = "author", length = 255, nullable = false)
    private String author;
    @Column(name = "overview", columnDefinition = "TEXT", length = 1000, nullable = true)
    private String overview;
    @Column(name = "pages", nullable = false)
    private Integer pages;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    public Book() { }

    public Book(String title, String author, String overview, Integer pages) {
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
