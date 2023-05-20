package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

import com.SpringProject.SpringBootProject.entity.Books;




@Entity
@Table(name = "Reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reports_id;
    @ManyToOne
    @JoinColumn(name = "books_id")
    private Books book;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @Column(name = "description")
    private String description;
    public Reports(){

    }

    public Reports(Books book, User user, String description) {
        this.book = book;
        this.user = user;
        this.description = description;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
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
