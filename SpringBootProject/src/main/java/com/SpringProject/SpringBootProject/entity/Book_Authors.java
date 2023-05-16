package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Book_Authors")
public class Book_Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long book_authors_id;
    @ManyToOne
    @JoinColumn(name = "books_id")
    private Books book;
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private Authors author;
    public Book_Authors(){

    }

    public Book_Authors(Books book, Authors author) {
        super();
        this.book = book;
        this.author = author;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }
}
