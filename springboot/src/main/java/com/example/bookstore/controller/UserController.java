package com.example.bookstore.controller;

import com.example.bookstore.entities.CartPosition;
import com.example.bookstore.entities.OrderDaO;
import com.example.bookstore.entities.User;
import com.example.bookstore.providers.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {
    private StorageService storageService;
    private AuthenticationManager authenticationManager;
    @Autowired
    public void setBookStorage(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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
    public String cart(Model model, @AuthenticationPrincipal User user, @RequestParam(required = false) Boolean successful) {
        List<CartPosition> books = storageService.getCartBooks(user.getId());
        model.addAttribute("cart", books);
        model.addAttribute("total", storageService.getCartTotal(user.getId()));
        model.addAttribute("cartEmpty", books.isEmpty());
        if (successful != null && !successful) {
            model.addAttribute("successful", successful);
        }
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

    @PostMapping("/createOrder")
    public String createOrder(Model model, @AuthenticationPrincipal User user) {
        boolean successful = storageService.makeOrder(user.getId());
        if (successful) {
            return "redirect:/orders";
        }
        else {
            return "redirect:/cart?successful=false";
        }
    }

    @GetMapping("/orders")
    public String showOrders(@AuthenticationPrincipal User user, Model model) {
        List<OrderDaO> orders = storageService.getOrders(user.getId());
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/createAccount")
    public String createAccount(@RequestParam("username") String username, @RequestParam("password") String password) {
        storageService.addUser(username, password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

}