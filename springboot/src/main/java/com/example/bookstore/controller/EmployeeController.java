package com.example.bookstore.controller;

import com.example.bookstore.entities.BookDaO;
import com.example.bookstore.providers.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private StorageService storageService;
    @Autowired
    public void setBookStorage(StorageService storageService) {
        this.storageService = storageService;
    }
    @GetMapping("/add-book")
    public String bookForm(Model model) {
        model.addAttribute("genres", storageService.getGenres());
        model.addAttribute("languages", storageService.getLanguages());
        return "bookForm";
    }
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute BookDaO bookDaO) {
        storageService.addBook(bookDaO);
        return "redirect:/";
    }
}