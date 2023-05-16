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
    private long surname;
    @Column(name = "birth")
    private String birth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private Cities city;

    @OneToMany(mappedBy = "author")
    private Set<Book_Authors> book_Authors = new HashSet<>();

   public Authors(){

   }

    public Authors(String name, long surname, String birth, Cities city, Set<Book_Authors> book_Authors) {
        super();
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.city = city;
        this.book_Authors = book_Authors;
    }
}
