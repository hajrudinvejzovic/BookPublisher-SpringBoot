package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

import java.awt.print.Book;

@Entity
@Table(name = "Reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reports_id;
    @ManyToOne
    @JoinColumn(name = "books_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @Column(name = "description")
    private String description;
    public Reports(){

    }

    public Reports(Book book, User user, String description) {
        super();
        this.book = book;
        this.user = user;
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
