package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

import java.awt.print.Book;

@Entity
@Table(name = "Book_Genres")
public class Book_Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "books_id")
    private Books book;
    @ManyToOne
    @JoinColumn(name = "genres_id")
    private Genres genre;
    public Book_Genres(){

    }

    public Book_Genres(Books book, Genres genre) {
        super();
        this.book = book;
        this.genre = genre;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }
}
