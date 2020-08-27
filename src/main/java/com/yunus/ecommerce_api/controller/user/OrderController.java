package com.yunus.ecommerce_api.controller.user;


import com.yunus.ecommerce_api.entity.*;
import com.yunus.ecommerce_api.model.Cart;
import com.yunus.ecommerce_api.response.ErrorResponse;
import com.yunus.ecommerce_api.service.*;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ProductService productService;

    @Autowired
    private WalletLogService walletLogService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody @Valid Cart cart, Authentication authentication){

        log.info("cart " + cart);

        User user = userService.findUserByUsername(authentication.getName());

        Integer totalPrice = cart.getTotalPrice();
        List<Cart.CartItems> cartItems = cart.getCartItems();



        Optional<UserAddress> userAddress = userAddressService.findById(cart.getAddressId());

        if (userAddress.isEmpty()){

            String message = "User Address doesn't have an id of " + cart.getAddressId();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));

        }

        UserAddress userAddress1 = userAddress.get();

        if (!userAddress1.getVerified()){

            String message = "Address id not verified - id of " + cart.getAddressId();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));

        }

        Order order = new Order();

        order.setCode(RandomString.make(8));
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus("ordered");
        order.setUser(user);
        order.setUserAddress(userAddress1);
        order.setDeliveryAddress(userAddress1.getName());

        List<OrderItems> orderItems = new ArrayList<>();

        int realTotalPrice = 0;

        try{


            for (Cart.CartItems cartItem: cartItems){

                OrderItems orderItem = new OrderItems();

                Optional<Product> product = productService.findById(cartItem.getProductId());

                if (product.isEmpty()){

                    String errMessage  = "There is no product with id " + cartItem.getProductId();
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errMessage));

                }

                orderItem.setProduct(product.get());

                orderItem.setOrder(order);
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setUnitPrice(product.get().getPrice());
                orderItem.setDate(new Timestamp(System.currentTimeMillis()));

                realTotalPrice += (product.get().getPrice() * cartItem.getQuantity());

                log.info("orderItem >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + orderItem);

                orderItems.add(orderItem);
            }

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));

        }

        if (user.getWalletBalance() < realTotalPrice){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Insufficient balance, " + user.getWalletBalance() + " < " + realTotalPrice));

        }

        order.setTotalPrice(realTotalPrice);

        order.setDate(new Timestamp(System.currentTimeMillis()));
        order.setOrderItems(orderItems);
        orderService.save(order);

        Integer walletBalance = user.getWalletBalance();
        user.setWalletBalance(walletBalance - realTotalPrice);
        userService.update(user);

        WalletLog walletLog = new WalletLog();

        walletLog.setType("debit");
        walletLog.setAmount(totalPrice);
        walletLog.setChargedAmount(totalPrice);
        walletLog.setUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }


    @GetMapping("/all/orders")
    public ResponseEntity<?> getOrders(Authentication authentication){

        User user = userService.findUserByUsername(authentication.getName());

        List<Order> orders = user.getOrders();

        return ResponseEntity.ok(orders);

    }



    public Cart addToCart(){

        Cart.CartItems cartItem1 = new Cart.CartItems();

        cartItem1.setProductId(2L);
        cartItem1.setPrice(1000);
        cartItem1.setQuantity(3);

        Cart.CartItems cartItem2 = new Cart.CartItems();

        List<Cart.CartItems> cartItems = new ArrayList<>();

        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        cartItem2.setProductId(3L);
        cartItem2.setPrice(600);
        cartItem2.setQuantity(2);

        Cart cart = new Cart();

        cart.setTotalPrice(4400);
        cart.setCartItems(cartItems);

        return cart;

    }

}
