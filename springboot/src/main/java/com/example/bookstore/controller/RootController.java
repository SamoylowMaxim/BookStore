package com.example.bookstore.controller;

import com.example.bookstore.entities.User;
import com.example.bookstore.providers.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class RootController {
    private StorageService storageService;
    @Autowired
    public void setBookStorage(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("books", storageService.getBooks());
        return "home";
    }
    @GetMapping("book/")
    public String book(Model model, @RequestParam int id, @AuthenticationPrincipal User user) {
        model.addAttribute("book", storageService.getBook(id));
        int amount = 0;
        if (user != null) {
            amount = storageService.getCartAmount(user.getId(), id);
        }
        model.addAttribute("amount", amount);
        return "book";
    }
    @GetMapping("cart")
    public String cart(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("cart", storageService.getCartBooks(user.getId()));
        model.addAttribute("total", storageService.getCartTotal(user.getId()));
        return "cart";
    }
    @PostMapping("addToCart")
    public String addToCart(@RequestParam("bookId") int id, @AuthenticationPrincipal User user) {
        storageService.addToCart(user.getId(), id);
        return "redirect:/book/?id="+id;
    }

    @PostMapping("subtractFromCart")
    public String subtractFromCart(@RequestParam("bookId") int id, @AuthenticationPrincipal User user) {
        storageService.subtractFromCart(user.getId(), id, 1);
        return "redirect:/book/?id="+id;
    }

    @PostMapping("removeFromCart")
    public String removeFromCart(@RequestParam("bookId") int id, @AuthenticationPrincipal User user) {
        storageService.subtractFromCart(user.getId(), id, storageService.getCartAmount(user.getId(), id));
        return "redirect:/book/?id="+id;
    }

    @PostMapping("subtractInCart")
    public String subtractInCart(@RequestParam("bookId") int id, @AuthenticationPrincipal User user) {
        storageService.subtractFromCart(user.getId(), id, 1);
        return "redirect:/cart";
    }
    @PostMapping("removeInCart")
    public String removeInCart(@RequestParam("bookId") int id, @AuthenticationPrincipal User user) {
        storageService.subtractFromCart(user.getId(), id, storageService.getCartAmount(user.getId(), id));
        return "redirect:/cart";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

}