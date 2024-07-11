package com.example.bookstore.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="books")
public class BookDaO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    private String name;
    private String author;
    @Column(name="book_language")
    private String language;
    @Column(name="publish_year")
    private int publishYear;
    private String genre;
    private String ISBN;
    private float price;
    private int pages;
    private String annotation;
    private Integer rating;
    @Column(name="is_new")
    private Boolean isNew;
    private int amount;
    private Integer cover;


    public Integer getId() {
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

    public Integer getRating() {
        return rating;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public int getAmount() {
        return amount;
    }
    public Integer getCover() {
        return cover;
    }

    public BookDaO() {
    }
    public BookDaO(Integer id, String name, String author, String language, int publishYear, String genre, String ISBN, float price, int pages, String annotation, Integer rating, Boolean isNew, int amount, Integer cover) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.language = language;
        this.publishYear = publishYear;
        this.genre = genre;
        this.ISBN = ISBN;
        this.price = price;
        this.pages = pages;
        this.annotation = annotation;
        this.rating = rating;
        if (isNew == null) {
            this.isNew = false;
        }
        else {
            this.isNew = isNew;
        }
        this.amount = amount;
        this.cover = cover;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDaO)) return false;
        BookDaO book = (BookDaO) o;
        return getPublishYear() == book.getPublishYear() &&
                getPages() == book.getPages() &&
                getAmount() == book.getAmount() &&
                Objects.equals(getName(), book.getName()) &&
                Objects.equals(getAuthor(), book.getAuthor()) &&
                Objects.equals(getLanguage(), book.getLanguage()) &&
                Objects.equals(getGenre(), book.getGenre()) &&
                Objects.equals(getISBN(), book.getISBN()) &&
                Float.compare(book.getPrice(), getPrice()) == 0 &&
                Objects.equals(getAnnotation(), book.getAnnotation()) &&
                Objects.equals(getRating(), book.getRating()) &&
                Objects.equals(getIsNew(), book.getIsNew()) &&
                Objects.equals(getCover(), book.getCover());
    }
}
