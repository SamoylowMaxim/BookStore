package com.example.bookstore;

import com.example.bookstore.providers.BookStorage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStore {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(BookStore.class, args);
    }

    @Bean(name = "bookStorage")
    public BookStorage getBookStorage() {
        return new BookStorage();
    }
}
