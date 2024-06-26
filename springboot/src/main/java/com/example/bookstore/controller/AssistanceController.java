package com.example.bookstore.controller;

import com.example.bookstore.entities.Book;
import com.example.bookstore.providers.BookStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/storeassistance")
public class AssistanceController {
    @Autowired
    private BookStorage bookStorage;
    @GetMapping("/add-book")
    public String assistance(Model model) {
        model.addAttribute("books", bookStorage.getBooks());
        return "assistance";
    }
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        bookStorage.addBook(book);
        return "redirect:/";
    }
}