package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;

import java.awt.print.Book;

@Entity
@Table(name = "Items_Per_Order")
public class Items_Per_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long items_per_order_id;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "books_id")
    private Books book;
    @Column(name = "quantity")
    private long quantity;
    public Items_Per_Order(){

    }

    public Items_Per_Order(Orders order, Books book, long quantity) {
        super();
        this.order = order;
        this.book = book;
        this.quantity = quantity;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
