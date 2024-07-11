package com.example.bookstore.providers;

import com.example.bookstore.entities.CartPositionDaO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartPositionDaO, Integer> {

    @Query("SELECT cp FROM CartPositionDaO cp JOIN cp.order o JOIN o.user u WHERE u.id = :userId AND o.status = 'Формируется'")
    List<CartPositionDaO> findByUserId(Integer userId);

    @Query("SELECT cp FROM CartPositionDaO cp JOIN cp.order o JOIN o.user u WHERE u.id = :userId AND cp.book.id = :bookId AND o.status = 'Формируется'")
    CartPositionDaO findByUserIdAndBookId(int userId, int bookId);

    //@Modifying
    //@Query("DELETE FROM CartPositionDaO cp WHERE cp.order.user.id = :userId AND cp.order.status = 'Формируется'")
    //void deleteByUserId(int userId);

    @Query("SELECT COALESCE(SUM(cp.amount * b.price), 0) FROM CartPositionDaO cp JOIN cp.order o JOIN o.user u JOIN cp.book b WHERE u.id = :userId AND o.status = 'Формируется'")
    Float getTotalPriceByUserId(Integer userId);

}
