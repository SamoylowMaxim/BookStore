package com.example.bookstore;

import com.example.bookstore.entities.BookDaO;
import com.example.bookstore.providers.DaOService;
import com.example.bookstore.providers.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookDaOStoreTests {
    @Autowired
    private StorageService storageService;
    @Autowired
    private DaOService daOService;

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
            storageService.addToCart(resultBook.getId());
        // Добавлена верная книга в корзину
        assertThat(storageService.getCartBooks().get(0).getId() == resultBook.getId()).isTrue();
        // Добавлено верное количество книг в корзину
        assertThat(storageService.getCartAmount(resultBook.getId()) == 10).isTrue();
        // Общая стоимость книг в корзине верно расчитана
        assertThat(storageService.getCartTotal() == 1).isTrue();
    }
}
