package com.example.bookstore.providers;

import com.example.bookstore.entities.BookDaO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DaOService {
    private static int count = 1;
    private static Map<Integer, BookDaO> booksMap = new HashMap<>();
    private static List<String> genres = List.of("Роман", "Фэнтези", "Детектив", "Научная фантастика");
    private static List<String> languages = List.of("Русский", "Английский", "Французский", "Итальянский");

    static {
        booksMap.put(count, new BookDaO(count, "Война и мир", "Л.Н.Толстой", "Русский",
                2022, "Роман", "978-5-389-06256-6", 500, 1300,
                "Хорошо известный классический роман-эпопея Льва Толстого о эпохе завоевательных походов Наполеона.",
                9, true, 3, 1));
        count++;
    }

    public static int getCount() {
        return count;
    }

    public List<BookDaO> getBooks() {
        return new ArrayList<>(booksMap.values());
    }

    public void addBook(BookDaO bookDaO) {
        bookDaO.setId(count);
        booksMap.put(count++, bookDaO);
    }

    public BookDaO getBook(int id) {
        if (booksMap.containsKey(id)) {
            return booksMap.get(id);
        }
        return null;
    }

    public BookDaO getBookById(Integer bookId) {
        if (booksMap.containsKey(bookId)) {
            return booksMap.get(bookId);
        }
        return null;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getLanguages() {
        return languages;
    }
}
