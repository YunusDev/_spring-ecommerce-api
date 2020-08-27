package com.yunus.ecommerce_api.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    @NotNull(message = "totalPrice cannot be empty")
    @Min(1)
    private Integer totalPrice;

    @NotNull(message = "totalPrice cannot be empty")
    @Min(1)
    private Long addressId;

    @NotEmpty(message = "cartItems cannot be empty")
    private List<CartItems> cartItems;

    public Cart() {
    }

    public Cart(Integer totalPrice, Long addressId, List<CartItems> cartItems) {
        this.totalPrice = totalPrice;
        this.addressId = addressId;
        this.cartItems = cartItems;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public static class CartItems{


        @NotNull(message = "productId cannot be empty")
        @Min(1)
        private Long productId;

        @NotNull(message = "price cannot be empty")
        @Min(1)
        private Integer price;

        @NotNull(message = "quantity cannot be empty")
        @Min(1)
        private Integer quantity;


        public CartItems() {
        }

        public CartItems(Long productId, Integer price, Integer quantity) {
            this.productId = productId;
            this.price = price;
            this.quantity = quantity;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "CartItems{" +
                    "productId=" + productId +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalPrice=" + totalPrice +
                ", cartItems=" + cartItems +
                '}';
    }
}


