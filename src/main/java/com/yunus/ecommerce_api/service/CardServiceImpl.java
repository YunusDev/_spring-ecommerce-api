package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.dao.CardRepository;
import com.yunus.ecommerce_api.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;


    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public void save(Card card) {

        card.setDate(new Date());
        cardRepository.save(card);

    }

    @Override
    public void deleteById(Long id) {

        cardRepository.deleteById(id);

    }

    @Override
    public Card findByUserAndTypeAndLast4(Long user_id, String type, Integer last4) {
        return cardRepository.findByUserAndTypeAndLast4(user_id, type, last4);
    }

    @Override
    public List<Card> findByCardDetails(String cardType) {
        return cardRepository.findByCardDetails(cardType);
    }

    @Override
    public void deleteAll() {

        cardRepository.deleteAll();
    }
}
