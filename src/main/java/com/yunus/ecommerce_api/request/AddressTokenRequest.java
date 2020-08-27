package com.yunus.ecommerce_api.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AddressTokenRequest implements Serializable {

    @NotEmpty(message = "token must not be empty")
    private String token;

    public AddressTokenRequest() {
    }

    public AddressTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
