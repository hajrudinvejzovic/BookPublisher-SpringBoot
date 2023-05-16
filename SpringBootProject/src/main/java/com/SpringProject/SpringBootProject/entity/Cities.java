package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Cities")
public class Cities {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cities_id;
    @Column(name = "name")
    private String name;
    @OneToOne(mappedBy = "user")
    private User user;
    @OneToOne(mappedBy = "city")
    private Authors author;
    @OneToOne(mappedBy = "city")
    private Employees employee;
    public Cities(){

    }

    public Cities(String name, User user, Authors author, Employees employee) {
        super();
        this.name = name;
        this.user = user;
        this.author = author;
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
}
