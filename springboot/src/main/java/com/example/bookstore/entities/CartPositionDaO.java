package com.example.bookstore.entities;

import javax.persistence.*;
@Entity
@Table(name="cart_positions")
public class CartPositionDaO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookDaO book;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CartPositionDaO() {

    }
    public CartPositionDaO(Integer id, BookDaO book, int amount, User user) {
        this.id = id;
        this.book = book;
        this.amount = amount;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BookDaO getBook() {
        return book;
    }
}
