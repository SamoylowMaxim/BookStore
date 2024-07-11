package com.example.bookstore.providers;

import com.example.bookstore.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StorageService {
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    @Qualifier("storageServicePasswordEncoder")
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        List<BookDaO> booksDaO;
        booksDaO = bookRepository.findAll();
        for (BookDaO bookDaO : booksDaO) {
            books.add(new Book(bookDaO.getId(), bookDaO.getName(), bookDaO.getAuthor(), bookDaO.getLanguage(), bookDaO.getPublishYear(), bookDaO.getGenre(), bookDaO.getISBN(), bookDaO.getPrice(), bookDaO.getPages(), bookDaO.getAnnotation(), bookDaO.getRating(), bookDaO.getIsNew(), bookDaO.getAmount(), bookDaO.getCover()));
        }
        return books;
    }

    public BookDaO addBook(BookDaO bookDaO) {
        return bookRepository.save(bookDaO);
    }

    public Book getBook(int id) {
        BookDaO bookDaO = bookRepository.findById(id);
        return new Book(bookDaO.getId(), bookDaO.getName(), bookDaO.getAuthor(), bookDaO.getLanguage(), bookDaO.getPublishYear(), bookDaO.getGenre(), bookDaO.getISBN(), bookDaO.getPrice(), bookDaO.getPages(), bookDaO.getAnnotation(), bookDaO.getRating(), bookDaO.getIsNew(), bookDaO.getAmount(), bookDaO.getCover());
    }
    @Transactional
    public void subtractFromCart(int user_id, int book_id, int amount) {
        CartPositionDaO oldCartPositionDaO = cartRepository.findByUserIdAndBookId(user_id, book_id);
        if (oldCartPositionDaO.getAmount()-amount <= 0) {
            cartRepository.delete(oldCartPositionDaO);
            OrderDaO order = orderRepository.findCurrentOrderByUserId(user_id);
            if (order != null && order.getCartPositions().size() == 1) {
                orderRepository.delete(order);
            }
        }
        else {
            oldCartPositionDaO.setAmount(oldCartPositionDaO.getAmount()-amount);
            cartRepository.save(oldCartPositionDaO);
        }
    }
    @Transactional
    public void addToCart(int user_id, int book_id) {
        CartPositionDaO oldCartPositionDaO = cartRepository.findByUserIdAndBookId(user_id, book_id);
        if (oldCartPositionDaO != null) {
            oldCartPositionDaO.setAmount(oldCartPositionDaO.getAmount()+1);
            cartRepository.save(oldCartPositionDaO);
        }
        else {
            OrderDaO order = orderRepository.findCurrentOrderByUserId(user_id);
            if (order == null) {
                order = orderRepository.save(new OrderDaO(null, new ArrayList<>(), "Формируется", userRepository.findById(user_id)));
            }
            CartPositionDaO newCartPositionDaO = new CartPositionDaO(null, bookRepository.findById(book_id), 1, order);
            order.getCartPositions().add(newCartPositionDaO);
            cartRepository.save(newCartPositionDaO);
            orderRepository.save(order);
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
        OrderDaO order = orderRepository.findCurrentOrderByUserId(id);
        for (CartPositionDaO cartPositionDaO : cartPositionsDaO) {
            CartPosition cartPosition = new CartPosition(cartPositionDaO.getId(), cartPositionDaO.getBook(), cartPositionDaO.getAmount(), order);
            booksInCart.add(cartPosition);
        }
        return booksInCart;
    }
    //public void clearCart(int id) {
    //    cartRepository.deleteByUserId(id);
    //}
    @Transactional
    public boolean makeOrder(int id) {
        OrderDaO order = orderRepository.findCurrentOrderByUserId(id);
        boolean hasChanged = false;
        for (CartPositionDaO cartPositionDaO : order.getCartPositions()) {
            boolean updateCart = false;
            while (bookRepository.findById(cartPositionDaO.getBook().getId()).get().getAmount() < cartPositionDaO.getAmount()) {
                hasChanged = true;
                updateCart = true;
                cartPositionDaO.setAmount(cartPositionDaO.getAmount()-1);
                if (cartPositionDaO.getAmount() == 0) {
                    updateCart = false;
                    cartRepository.delete(cartPositionDaO);
                    if (order.getCartPositions().size() == 1) {
                        orderRepository.delete(order);
                    }
                    else {
                        order.getCartPositions().remove(cartPositionDaO);
                        orderRepository.save(order);
                    }
                }
            }
            if (updateCart) {
                cartRepository.save(cartPositionDaO);
            }
        }
        if (!hasChanged) {
            for (CartPositionDaO cartPositionDaO : order.getCartPositions()) {
                BookDaO book = bookRepository.findById(cartPositionDaO.getBook().getId()).get();
                book.setAmount(book.getAmount() - cartPositionDaO.getAmount());
                bookRepository.save(book);
            }
            order.setStatus("Оформлено");
            order.setFinished();
            orderRepository.save(order);
        }
        return !hasChanged;
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

    public List<OrderDaO> getOrders(int id) {
        return orderRepository.findByUserId(id);
    }

    public void addUser(String username, String password) {
        userRepository.save(new User(null, username, passwordEncoder.encode(password), "ROLE_USER"));
    }
}
