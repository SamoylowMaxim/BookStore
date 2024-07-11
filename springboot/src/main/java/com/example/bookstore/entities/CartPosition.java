package com.example.bookstore.entities;
public class CartPosition extends CartPositionDaO {
    private final float total;

    public CartPosition(Integer id, BookDaO book, int amount, OrderDaO order) {
        super(id, book, amount, order);
        this.total = (float) (Math.round(book.getPrice() * amount * 100.0) / 100.0);
    }

    public float getTotal() {
        return total;
    }

}
