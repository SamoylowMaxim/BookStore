package com.example.bookstore.providers;

import com.example.bookstore.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    User findById(int id);
    User findByLogin(String login);
    int curUser = 1;

}
