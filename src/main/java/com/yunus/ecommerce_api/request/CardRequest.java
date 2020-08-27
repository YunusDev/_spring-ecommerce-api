package com.yunus.ecommerce_api.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CardRequest implements Serializable {

    @NotEmpty(message = "txref must not be empty")
    private String txref;

    public CardRequest() {
    }

    public CardRequest(String txref) {
        this.txref = txref;
    }

    public String getTxref() {
        return txref;
    }

    public void setTxref(String txref) {
        this.txref = txref;
    }
}
