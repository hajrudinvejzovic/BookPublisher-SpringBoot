package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;

import java.time.LocalDate;

@Entity
@Table(name = "Payment")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long payment_id;
    @OneToOne(mappedBy = "payment")
    private User user;
    @Column(name = "users_id")
    private long users_id;
    @OneToOne(mappedBy = "payment")
    private Orders order;
    @Column(name = "amount")
    private int amount;
    @Column(name = "payment_date")
    private LocalDate payment_date;
    public Payments(){

    }

    public Payments(User user, long users_id, Orders order, int amount, LocalDate payment_date) {
        this.user = user;
        this.users_id = users_id;
        this.order = order;
        this.amount = amount;
        this.payment_date = payment_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUsers_id() {
        return users_id;
    }

    public void setUsers_id(long users_id) {
        this.users_id = users_id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date = payment_date;
    }
}
