package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Authors")
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authors_id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birth")
    private String birth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private Cities city;

    @OneToMany(mappedBy = "author")
    private Set<Book_Authors> book_Authors = new HashSet<>();

   public Authors(){

   }

    public Authors(String name, String surname, String birth, Cities city, Set<Book_Authors> book_Authors) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.city = city;
        this.book_Authors = book_Authors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Set<Book_Authors> getBook_Authors() {
        return book_Authors;
    }

    public void setBook_Authors(Set<Book_Authors> book_Authors) {
        this.book_Authors = book_Authors;
    }
}
