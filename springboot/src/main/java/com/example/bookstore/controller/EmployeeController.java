package com.example.bookstore.controller;

import com.example.bookstore.entities.BookDaO;
import com.example.bookstore.providers.CacheService;
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
    private CacheService cacheService;
    @Autowired
    public void setBookStorage(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
    @GetMapping("/add-book")
    public String bookForm(Model model) {
        model.addAttribute("genres", storageService.getGenres());
        model.addAttribute("languages", storageService.getLanguages());
        cacheService.setPage("/add-book");
        return "bookForm";
    }
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("bookDaO") BookDaO bookDaO) {
        storageService.addBook(new BookDaO(null, bookDaO.getName(), bookDaO.getAuthor(), bookDaO.getLanguage(), bookDaO.getPublishYear(), bookDaO.getGenre(), bookDaO.getISBN(), bookDaO.getPrice(), bookDaO.getPages(), bookDaO.getAnnotation(), bookDaO.getRating(), bookDaO.isNew(), bookDaO.getAmount(), bookDaO.getCover()));
        return "redirect:/";
    }
}