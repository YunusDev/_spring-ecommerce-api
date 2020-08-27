package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    public List<Card> findAll();
    public Optional<Card> findById(Long id);
    public void save(Card card);
    public void deleteById(Long id);
    public Card findByUserAndTypeAndLast4(Long user_id, String cardType, Integer last4);
    public List<Card> findByCardDetails(String cardType);
    public void deleteAll();
}
