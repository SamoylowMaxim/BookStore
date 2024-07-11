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
    @JoinColumn(name = "order_id")
    private OrderDaO order;

    public CartPositionDaO() {

    }
    public CartPositionDaO(Integer id, BookDaO book, int amount, OrderDaO order) {
        this.id = id;
        this.book = book;
        this.amount = amount;
        this.order = order;
    }

    public void setBook(BookDaO book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BookDaO getBook() {
        return book;
    }

    public OrderDaO getOrder() {
        return order;
    }

    public void setOrder(OrderDaO order) {
        this.order = order;
    }
}
