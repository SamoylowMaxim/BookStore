package com.example.bookstore;

import com.example.bookstore.entities.BookDaO;
import com.example.bookstore.providers.DaOService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStore {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(BookStore.class, args);
    }

    @Bean
    public CommandLineRunner init(DaOService repository) {
        return (args) -> {
            repository.save(new BookDaO(null,
                    "Война и мир",
                    "Л.Н.Толстой",
                    "Русский",
                    2022,
                    "Роман",
                    "978-5-389-06256-6",
                    500,
                    1300,
                    "Хорошо известный классический роман-эпопея Льва Толстого о эпохе завоевательных походов Наполеона.",
                    9,
                    true,
                    3,
                    1));
        };
    }
}
