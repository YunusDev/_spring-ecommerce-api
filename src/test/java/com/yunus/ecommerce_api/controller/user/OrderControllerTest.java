package com.yunus.ecommerce_api.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunus.ecommerce_api.entity.Category;
import com.yunus.ecommerce_api.entity.Product;
import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.entity.UserAddress;
import com.yunus.ecommerce_api.model.Cart;
import com.yunus.ecommerce_api.service.*;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Ignore
public class OrderControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    private String jwtKey;
    private User user;

    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    @Before
    public  void before(){

        User user = new User( "Yunus", "YunusDev",
                "tayos@gmail.com", "09303884384", "password");

        user.setWalletBalance(23000);

        userService.update(user);


        setJwtKey("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJZdW51c0RldiIsImV4cCI6MTU5ODU1N" +
                "zIzNiwiaWF0IjoxNTk4NTIxMjM2fQ.Pzl_5kihmEJrJPlAX2bA0SPUrxhh1SRmZ-xdaFWrBxw");

    }

    @After
    public  void after(){
        userAddressService.deleteAll();
        orderService.deleteAll();
        userService.deleteAll();

    }


    @Test
    @DisplayName("Test checkout")
    public void testCheckOut() throws Exception{

        User user = userService.findUserByUsername("YunusDev");

        System.out.println("user " + user.getWalletBalance());

        UserAddress address = new UserAddress("Lagos", "ahghdhdjnnd", true, new Timestamp(System.currentTimeMillis()), user);

        userAddressService.save(address);

        Category cat1 = new Category( "mobile", "mobile", new Date());
        Category cat2 = new Category( "makeup", "makeup", new Date());

        categoryService.save(cat1);
        categoryService.save(cat2);

        Product product1 = new Product( "iphone 12", true, 14,
                2000, "A very good mobile", new Date(), cat1);

        Product product2 = new Product( "Make up brush", true, 11,
                1000, "A very good brush makup", new Date(), cat2);

        productService.save(product1);
        productService.save(product2);

        Cart cart = new Cart();

        cart.setTotalPrice(5200);
        cart.setAddressId(address.getId());

        List<Cart.CartItems> cartItems = new ArrayList<Cart.CartItems>();

        int realTotalPrice = 0;

        Cart.CartItems cartItems1 = new Cart.CartItems();

        cartItems1.setPrice(1000);
        cartItems1.setProductId(product1.getId());
        cartItems1.setQuantity(2);

        realTotalPrice += (product1.getPrice() * cartItems1.getQuantity());

        cartItems.add(cartItems1);

        Cart.CartItems cartItems2 = new Cart.CartItems();

        cartItems2.setPrice(2000);
        cartItems2.setProductId(product2.getId());
        cartItems2.setQuantity(5);

        realTotalPrice += (product2.getPrice() * cartItems2.getQuantity());

        cartItems.add(cartItems2);

        cart.setCartItems(cartItems);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/checkout")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cart)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("ordered")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice", Matchers.is(realTotalPrice)));

        Assertions.assertEquals(realTotalPrice, 9000);

    }

    @Test
    @DisplayName("Testing For success getting of user orders")
    public void testForSuccessGettingOrders() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/all/orders")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
