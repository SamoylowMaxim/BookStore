package com.example.bookstore.providers;

import com.example.bookstore.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StorageService {
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        List<BookDaO> booksDaO;
        booksDaO = bookRepository.findAll();
        for (BookDaO bookDaO : booksDaO) {
            books.add(new Book(bookDaO.getId(), bookDaO.getName(), bookDaO.getAuthor(), bookDaO.getLanguage(), bookDaO.getPublishYear(), bookDaO.getGenre(), bookDaO.getISBN(), bookDaO.getPrice(), bookDaO.getPages(), bookDaO.getAnnotation(), bookDaO.getRating(), bookDaO.isNew(), bookDaO.getAmount(), bookDaO.getCover()));
        }
        return books;
    }

    public BookDaO addBook(BookDaO bookDaO) {
        return bookRepository.save(bookDaO);
    }

    public Book getBook(int id) {
        BookDaO bookDaO = bookRepository.findById(id);
        return new Book(bookDaO.getId(), bookDaO.getName(), bookDaO.getAuthor(), bookDaO.getLanguage(), bookDaO.getPublishYear(), bookDaO.getGenre(), bookDaO.getISBN(), bookDaO.getPrice(), bookDaO.getPages(), bookDaO.getAnnotation(), bookDaO.getRating(), bookDaO.isNew(), bookDaO.getAmount(), bookDaO.getCover());
    }

    public void subtractFromCart(int user_id, int book_id, int amount) {
        CartPositionDaO oldCartPositionDaO = cartRepository.findByUserIdAndBookId(user_id, book_id);
        if (oldCartPositionDaO.getAmount()-amount <= 0) {
            cartRepository.delete(oldCartPositionDaO);
        }
        else {
            oldCartPositionDaO.setAmount(oldCartPositionDaO.getAmount()-amount);
            cartRepository.save(oldCartPositionDaO);
        }
    }
    public void addToCart(int user_id, int book_id) {
        CartPositionDaO oldCartPositionDaO = cartRepository.findByUserIdAndBookId(user_id, book_id);
        if (oldCartPositionDaO != null) {
            oldCartPositionDaO.setAmount(oldCartPositionDaO.getAmount()+1);
            cartRepository.save(oldCartPositionDaO);
        }
        else {
            CartPositionDaO newCartPositionDaO = new CartPositionDaO(null, bookRepository.findById(book_id), 1, userRepository.findById(user_id));
            cartRepository.save(newCartPositionDaO);
        }
    }

    public Integer getCartAmount(int user_id, int book_id) {
        CartPositionDaO oldCartPositionDaO = cartRepository.findByUserIdAndBookId(user_id, book_id);
        if (oldCartPositionDaO != null) {
            return oldCartPositionDaO.getAmount();
        }
        else {
            return 0;
        }
    }

    public List<CartPosition> getCartBooks(int id) {
        List<CartPositionDaO> cartPositionsDaO = cartRepository.findByUserId(id);
        List<CartPosition> booksInCart = new ArrayList<>();
        for (CartPositionDaO cartPositionDaO : cartPositionsDaO) {
            CartPosition cartPosition = new CartPosition(cartPositionDaO.getId(), cartPositionDaO.getBook(), cartPositionDaO.getAmount(), userRepository.findById(UserRepository.curUser));
            booksInCart.add(cartPosition);
        }
        return booksInCart;
    }
    public float getCartTotal(int id) {
        return cartRepository.getTotalPriceByUserId(id);
    }
    public List<String> getGenres() {
        return bookRepository.getGenres();
    }
    public List<String> getLanguages() {
        return bookRepository.getLanguages();
    }
}
