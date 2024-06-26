package com.example.bookstore.providers;

import com.example.bookstore.entities.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookStorage {
    private List<Book> books = new ArrayList<>(List.of(
            new Book("Война и мир", "Л.Н.Толстой", "Русский",
                    "Азбука", 2022, "Роман", "978-5-389-06256-6", 500, 1300,
                    "Хорошо известный классический роман-эпопея Льва Толстого рассказывает о сложном, бурном периоде в истории России и всей Европы — эпохе завоевательных походов императора Наполеона в Восточную Европу и Россию, с 1805 по 1812 год. Автор подробно рассказывает о Войне — о ходе боевых действий от Аустерлица до Бородино и Березины; и о Мире — показана жизнь в России в это же время, причем пером писателя охвачены все слои общества — дворянские семьи, крестьяне, горожане, солдаты и даже императоры.",
                    9, false, 100)));

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
