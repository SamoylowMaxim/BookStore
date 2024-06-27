package com.example.bookstore.controller;

import com.example.bookstore.providers.ServiceStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class RootController {
    private ServiceStorage serviceStorage;
    @Autowired
    public void setBookStorage(ServiceStorage serviceStorage) {
        this.serviceStorage = serviceStorage;
    }
    @GetMapping
    public String home(Model model) {
        model.addAttribute("books", serviceStorage.getBooks());
        return "home";
    }
}