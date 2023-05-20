package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long users_id;
    @OneToMany(mappedBy = "user")
    private Set<Reports> report = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<Orders> order = new HashSet<>();
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cities_id")
    private Cities city;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts_id")
    private Contacts contact;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payments_id")
    private Payments payment;
    public User(){

    }

    public User(Set<Reports> report, Set<Orders> order, String name, String surname, Cities city, Contacts contact, Payments payment) {
        this.report = report;
        this.order = order;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.contact = contact;
        this.payment = payment;
    }

    public Set<Reports> getReport() {
        return report;
    }

    public void setReport(Set<Reports> report) {
        this.report = report;
    }

    public Set<Orders> getOrder() {
        return order;
    }

    public void setOrder(Set<Orders> order) {
        this.order = order;
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

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Contacts getContact() {
        return contact;
    }

    public void setContact(Contacts contact) {
        this.contact = contact;
    }

    public Payments getPayment() {
        return payment;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }
}
