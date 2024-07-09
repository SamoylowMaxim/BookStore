package com.example.bookstore;

import com.example.bookstore.entities.BookDaO;
import com.example.bookstore.providers.BookRepository;
import com.example.bookstore.providers.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookDaOStoreTests {
    @Autowired
    private StorageService storageService;
    @Autowired
    private BookRepository bookRepository;

    private final int user_id = 1;
    void init() {

    }

    @Test
    void addBook() {
        BookDaO testBook = new BookDaO(null,
                "Война и мир",
                "Л.Н.Толстой",
                "Русский",
                2022,
                "Роман",
                "978-5-389-06256-6",
                33.33f,
                1300,
                "Хорошо известный классический роман-эпопея Льва Толстого о эпохе завоевательных походов Наполеона.",
                9,
                true,
                3,
                1);
        BookDaO resultBook = storageService.addBook(testBook);
        // Книгу можно получить из БД
        assertThat(storageService.getBooks().isEmpty()).isFalse();
        // Полученная книга соответсвует добавленной
        assertThat(resultBook.equals(testBook)).isTrue();
    }

    @Test
    void findBook() {
        BookDaO testBook = new BookDaO(null,
                "Война и мир",
                "Л.Н.Толстой",
                "Русский",
                2022,
                "Роман",
                "978-5-389-06256-6",
                33.33f,
                1300,
                "Хорошо известный классический роман-эпопея Льва Толстого о эпохе завоевательных походов Наполеона.",
                9,
                true,
                3,
                1);
        int id = storageService.addBook(testBook).getId();
        BookDaO resultBook = storageService.getBook(id);
        // Поиск книги по id выдал верную книгу
        assertThat(resultBook.equals(testBook)).isTrue();
    }

    @Test
    void addToCart() {
        BookDaO testBook = new BookDaO(null,
                "Война и мир",
                "Л.Н.Толстой",
                "Русский",
                2022,
                "Роман",
                "978-5-389-06256-6",
                0.1f,
                1300,
                "Хорошо известный классический роман-эпопея Льва Толстого о эпохе завоевательных походов Наполеона.",
                9,
                true,
                10,
                1);
        BookDaO resultBook = storageService.addBook(testBook);
        for (int i = 1; i<=10; i++)
            storageService.addToCart(user_id, resultBook.getId());
        // Добавлена верная книга в корзину
        assertThat(storageService.getCartBooks(user_id).get(0).getBook().equals(resultBook)).isTrue();
        // Добавлено верное количество книг в корзину
        assertThat(storageService.getCartAmount(user_id, resultBook.getId()) == 10).isTrue();
        // Общая стоимость книг в корзине верно расчитана
        assertThat(storageService.getCartTotal(user_id) == 1).isTrue();
    }
}
