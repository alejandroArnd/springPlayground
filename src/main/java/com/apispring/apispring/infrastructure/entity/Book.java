package com.apispring.apispring.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}
