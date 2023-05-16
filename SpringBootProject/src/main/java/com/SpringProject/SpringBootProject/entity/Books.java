package com.SpringProject.SpringBootProject.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Books")
public class Books {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long books_id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "book")
    private Set<Book_Genres> book_Genres = new HashSet<>() ;

   @OneToMany(mappedBy = "book")
    private Set<Book_Authors> book_Authors = new HashSet<>();
    @OneToMany(mappedBy = "book")
    private Set<Book_Languages> book_Languages = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Items_Per_Order> items_Per_Orders = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Reports> reports = new HashSet<>();

    @Column(name = "publishing_year")
    private int publishing_year;
    @Column(name = "price")
    private int price;
    @Column(name = "bestseller")
    private boolean bestseller;
    @Column(name = "isbn")
    private int isbn;
    @Column(name = "total_pages")
    private int total_pages;
    @Column(name = "in_stock")
    private boolean in_stock;
    @Column(name = "availability")
    private boolean availability;

    public Books(){

    }

    public Books(String name, Set<Book_Genres> book_Genres, Set<Book_Authors> book_Authors, Set<Book_Languages> book_Languages, Set<Items_Per_Order> items_Per_Orders, Set<Reports> reports, int publishing_year, int price, boolean bestseller, int isbn, int total_pages, boolean in_stock, boolean availability) {
        super();
        this.name = name;
        this.book_Genres = book_Genres;
        this.book_Authors = book_Authors;
        this.book_Languages = book_Languages;
        this.items_Per_Orders = items_Per_Orders;
        this.reports = reports;
        this.publishing_year = publishing_year;
        this.price = price;
        this.bestseller = bestseller;
        this.isbn = isbn;
        this.total_pages = total_pages;
        this.in_stock = in_stock;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book_Genres> getBook_Genres() {
        return book_Genres;
    }

    public void setBook_Genres(Set<Book_Genres> book_Genres) {
        this.book_Genres = book_Genres;
    }

    public Set<Book_Authors> getBook_Authors() {
        return book_Authors;
    }

    public void setBook_Authors(Set<Book_Authors> book_Authors) {
        this.book_Authors = book_Authors;
    }

    public Set<Book_Languages> getBook_Languages() {
        return book_Languages;
    }

    public void setBook_Languages(Set<Book_Languages> book_Languages) {
        this.book_Languages = book_Languages;
    }

    public Set<Items_Per_Order> getItems_Per_Orders() {
        return items_Per_Orders;
    }

    public void setItems_Per_Orders(Set<Items_Per_Order> items_Per_Orders) {
        this.items_Per_Orders = items_Per_Orders;
    }

    public Set<Reports> getReports() {
        return reports;
    }

    public void setReports(Set<Reports> reports) {
        this.reports = reports;
    }

    public int getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(int publishing_year) {
        this.publishing_year = publishing_year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBestseller() {
        return bestseller;
    }

    public void setBestseller(boolean bestseller) {
        this.bestseller = bestseller;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public boolean isIn_stock() {
        return in_stock;
    }

    public void setIn_stock(boolean in_stock) {
        this.in_stock = in_stock;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
