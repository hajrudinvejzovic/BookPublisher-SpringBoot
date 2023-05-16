package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Genres")
public class Genres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long genres_id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Book_Genres> BookGenres = new HashSet<>();
    public Genres(){

    }

    public Genres(String name, Set<Book_Genres> bookGenres) {
        super();
        this.name = name;
        BookGenres = bookGenres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book_Genres> getBookGenres() {
        return BookGenres;
    }

    public void setBookGenres(Set<Book_Genres> bookGenres) {
        BookGenres = bookGenres;
    }
}
