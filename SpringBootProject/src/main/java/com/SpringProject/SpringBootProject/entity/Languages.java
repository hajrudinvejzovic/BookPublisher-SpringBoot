package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Languages")
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long languages_id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "languages")
    private Set<Book_Languages> book_Languages = new HashSet<>();
    public Languages(){

    }

    public Languages(String name, Set<Book_Languages> book_Languages) {
        super();
        this.name = name;
        this.book_Languages = book_Languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book_Languages> getBook_Languages() {
        return book_Languages;
    }

    public void setBook_Languages(Set<Book_Languages> book_Languages) {
        this.book_Languages = book_Languages;
    }
}
