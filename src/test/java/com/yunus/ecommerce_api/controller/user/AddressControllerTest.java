package com.yunus.ecommerce_api.controller.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.entity.UserAddress;
import com.yunus.ecommerce_api.request.AddressRequest;
import com.yunus.ecommerce_api.request.AddressTokenRequest;
import com.yunus.ecommerce_api.service.*;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Ignore
public class AddressControllerTest {

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
    @DisplayName("Test for fetching address")
    public void testForFetchingAddress() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/address")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }


    //***********************************************************************************************

    @Test
    @DisplayName("Test for when address name is not passed")
    public void testForFailWhenAddressNameIsNotPassed() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/address")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(new AddressRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("address name must not be empty")));

    }

    @Test
    @DisplayName("Test for success when adding address")
    public void testForSuccessForAddingAddress() throws Exception{

        AddressRequest request = new AddressRequest("Lagos");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/address")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Lagos")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.verified", Matchers.is(false)));

    }

//    ***********************************************************************************************


    @Test
    @DisplayName("Test when token is not passed for resend address")
    public void testForTokenNotPassedResendAddress() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/resend/address")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(new AddressTokenRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token", Matchers.is("token must not be empty")));

    }

    @Test
    @DisplayName("Test when token is not valid - /api/resend/address")
    public void testForWhenTokenIsNotValid() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/resend/address")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(new AddressTokenRequest("xxxxxxxxxxxxxx")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    @DisplayName("Test for success resend address")
    public void testForSuccessResendAddress() throws Exception{

        User user = userService.findUserByUsername("YunusDev");

        System.out.println("user " + user);

        AddressRequest request = new AddressRequest("Lagos");

        UserAddress userAddress = new UserAddress();

        userAddress.setName(request.getName());
        String token = RandomString.make(60);
        userAddress.setToken(token);
        userAddress.setVerified(false);
        userAddress.setUser(user);
        userAddress.setDate(new Timestamp(System.currentTimeMillis()));

        userAddressService.save(userAddress);

        AddressTokenRequest addressTokenRequest = new AddressTokenRequest(userAddress.getToken());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/resend/address")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(addressTokenRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token", Matchers.is(userAddress.getToken())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(userAddress.getName())));

    }

//    ***********************************************************************************************

    @Test
    @DisplayName("Test For Invalid confirm address")
    public void testForInvalidConfirmAddress() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/confirm/address/{token}", "xxxxxxxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        System.out.println("result " + result.getResponse().getContentAsString());

        Assertions.assertThat("<h3 style='color: red'>Invalid Url</h1>").isEqualTo(result.getResponse().getContentAsString());


    }

    @Test
    @DisplayName("Test For success confirm address")
    public void testForSuccessConfirmAddress() throws Exception{

        User user = userService.findUserByUsername("YunusDev");

        System.out.println("user " + user);

        AddressRequest request = new AddressRequest("Lagos");

        UserAddress userAddress = new UserAddress();

        userAddress.setName(request.getName());
        String token = RandomString.make(60);
        userAddress.setToken(token);
        userAddress.setVerified(false);
        userAddress.setUser(user);
        userAddress.setDate(new Timestamp(System.currentTimeMillis()));

        userAddressService.save(userAddress);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/confirm/address/{token}", userAddress.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assertions.assertThat("<h3 style='color: green'>Your address has been verified successfully;</h1>").isEqualTo(result.getResponse().getContentAsString());


    }



}
