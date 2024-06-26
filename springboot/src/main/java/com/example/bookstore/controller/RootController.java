package com.example.bookstore.controller;

import com.example.bookstore.providers.BookStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class RootController {
    @Autowired
    private BookStorage bookStorage;
    @GetMapping
    public String home(Model model) {
        model.addAttribute("books", bookStorage.getBooks());
        return "home";
    }
}