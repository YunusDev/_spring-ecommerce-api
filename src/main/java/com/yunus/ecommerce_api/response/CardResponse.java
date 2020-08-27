package com.yunus.ecommerce_api.response;

import com.yunus.ecommerce_api.entity.Card;

public class CardResponse {

    public Boolean error;

    public Boolean newCard;

    public String message;

    public Card card;

    public CardResponse() {
    }

    public CardResponse(Boolean error, Boolean newCard, String message, Card card) {
        this.error = error;
        this.newCard = newCard;
        this.message = message;
        this.card = card;
    }

    public CardResponse(Boolean error, Card card) {
        this.error = error;
        this.card = card;
    }

    public Boolean getNewCard() {
        return newCard;
    }

    public void setNewCard(Boolean newCard) {
        this.newCard = newCard;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
