package com.example.bookstore.controller;

import com.example.bookstore.entities.BookDaO;
import com.example.bookstore.providers.CacheService;
import com.example.bookstore.providers.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private StorageService storageService;
    private CacheService cacheService;
    @Autowired
    public void setStorageService(StorageService storageService) {
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
        return "bookForm";
    }
    @PostMapping("/addBook")
    public ResponseEntity<BookDaO> addBook(@RequestBody BookDaO bookDaO) {
        BookDaO newBook = storageService.addBook(bookDaO);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
}