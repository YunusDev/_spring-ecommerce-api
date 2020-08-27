package com.yunus.ecommerce_api.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AddressRequest implements Serializable {

    @NotEmpty(message = "address name must not be empty")
    private String name;



    public AddressRequest() {
    }

    public AddressRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
