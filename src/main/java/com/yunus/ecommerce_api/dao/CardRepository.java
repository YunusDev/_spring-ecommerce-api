package com.yunus.ecommerce_api.dao;

import com.yunus.ecommerce_api.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT * FROM cards WHERE user_id = :user_id AND card_type = :cardType AND last4 = :last4", nativeQuery = true)
    public Card findByUserAndTypeAndLast4(@Param("user_id") Long user_id, @Param("cardType") String cardType, @Param("last4") Integer last4);

    @Query(value = "SELECT * FROM cards WHERE card_type = :cardType", nativeQuery = true)
    public List<Card> findByCardDetails(@Param("cardType") String cardType);



}
