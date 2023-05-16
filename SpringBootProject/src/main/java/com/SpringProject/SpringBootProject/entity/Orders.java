package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orders_id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @Column(name = "total_quantity")
    private long total_quantity;
    @Column(name = "total_price")
    private long total_price;
    @OneToMany(mappedBy = "order")
    private Set<Items_Per_Order> order = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payments payment;


    public Orders(){

    }

    public Orders(User user, long total_quantity, long total_price, Set<Items_Per_Order> order, Payments payment) {
        super();
        this.user = user;
        this.total_quantity = total_quantity;
        this.total_price = total_price;
        this.order = order;
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(long total_quantity) {
        this.total_quantity = total_quantity;
    }

    public long getTotal_price() {
        return total_price;
    }

    public void setTotal_price(long total_price) {
        this.total_price = total_price;
    }

    public Set<Items_Per_Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Items_Per_Order> order) {
        this.order = order;
    }

    public Payments getPayment() {
        return payment;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }
}
