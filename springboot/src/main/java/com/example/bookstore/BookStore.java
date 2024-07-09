package com.example.bookstore;

import com.example.bookstore.entities.BookDaO;
import com.example.bookstore.entities.User;
import com.example.bookstore.providers.BookRepository;
import com.example.bookstore.providers.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookStore {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(BookStore.class, args);
    }

    @Bean
    public CommandLineRunner init(BookRepository bookRepository, UserRepository userRepository, @Autowired BCryptPasswordEncoder passwordEncoder) {
        return (args) -> {
            userRepository.save(new User(null, "log", passwordEncoder.encode("123"), "ROLE_USER"));
            userRepository.save(new User(null, "log2", passwordEncoder.encode("123"), "ROLE_USER"));
            userRepository.save(new User(null, "admin", passwordEncoder.encode("123"), "ROLE_ADMIN"));
            bookRepository.save(new BookDaO(null,
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
