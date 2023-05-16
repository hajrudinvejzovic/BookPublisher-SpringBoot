package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

import java.awt.print.Book;

@Entity
@Table(name = "Book_Languages")
public class Book_Languages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long book_languages_id;
    @ManyToOne
    @JoinColumn(name = "books_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "languages_id")
    private Languages languages;
    public Book_Languages(){

    }

    public Book_Languages(Book book, Languages languages) {
        super();
        this.book = book;
        this.languages = languages;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }
}
