package com.yunus.ecommerce_api.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ChargeTokenRequest implements Serializable {

    @NotNull(message = "cardId must not be empty")
    @Min(1)
    private Long cardId;

    @NotNull(message = "amount must not be empty")
    @Min(1)
    private Integer amount;


    public ChargeTokenRequest() {
    }

    public ChargeTokenRequest(Long cardId, Integer amount) {
        this.cardId = cardId;
        this.amount = amount;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
