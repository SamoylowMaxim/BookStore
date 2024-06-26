package com.example.bookstore.entities;

public class Book {
    static private int count = 1;
    private int id;
    private String name;
    private String author;
    private String language;
    private String publisher;
    private int publishYear;
    private String genre;
    private String ISBN;
    private float price;
    private int pages;
    private String annotation;
    private int rating;
    private boolean isNew;
    private int amount;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public float getPrice() {
        return price;
    }

    public int getPages() {
        return pages;
    }

    public String getAnnotation() {
        return annotation;
    }

    public int getRating() {
        return rating;
    }

    public boolean isNew() {
        return isNew;
    }

    public int getAmount() {
        return amount;
    }

    public Book(String name, String author, String language, String publisher, int publishYear, String genre, String ISBN, float price, int pages, String annotation, int rating, boolean isNew, int amount) {
        this.id = count++;
        this.name = name;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.genre = genre;
        this.ISBN = ISBN;
        this.price = price;
        this.pages = pages;
        this.annotation = annotation;
        this.rating = rating;
        this.isNew = isNew;
        this.amount = amount;
    }
}
