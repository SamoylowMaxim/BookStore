package com.example.bookstore.providers;

import com.example.bookstore.entities.CartPositionDaO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartPositionDaO, Integer> {

    List<CartPositionDaO> findByUserId(Integer userId);

    CartPositionDaO findByUserIdAndBookId(int curUser, int id);

    @Query("SELECT COALESCE(SUM(cp.amount * b.price), 0) FROM CartPositionDaO cp JOIN cp.book b WHERE cp.user.id = :userId")
    Float getTotalPriceByUserId(Integer userId);
}
