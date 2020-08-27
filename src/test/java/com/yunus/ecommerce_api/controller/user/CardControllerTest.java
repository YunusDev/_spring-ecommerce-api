package com.yunus.ecommerce_api.controller.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunus.ecommerce_api.entity.Card;
import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.request.CardRequest;
import com.yunus.ecommerce_api.request.ChargeTokenRequest;
import com.yunus.ecommerce_api.service.CardService;
import com.yunus.ecommerce_api.service.UserService;
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

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Ignore
public class CardControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Autowired
    private MockMvc mockMvc;

    private String jwtKey;
    private User user;

    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Before
    public  void before(){

        User user = new User( "Yunus", "YunusDev",
                "tayos@gmail.com", "09303884384", "password");


        userService.save(user);

//        setUser(user);

//        setJwtKey("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJZdW51c0RldiIsImV4cCI6MTU5" +
//                "NjgzNjYxOSwiaWF0IjoxNTk2ODAwNjE5fQ.YGap8BeZX2G0_0WnR11Al-7uq_VCpzeLubO2a2fLl9Q");

        setJwtKey("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJZdW51c0RldiIsImV4cCI6MTU5ODU1N" +
                "zIzNiwiaWF0IjoxNTk4NTIxMjM2fQ.Pzl_5kihmEJrJPlAX2bA0SPUrxhh1SRmZ-xdaFWrBxw");

    }

    @After
    public  void after(){

       cardService.deleteAll();
        userService.deleteAll();

    }

    @Test
    @DisplayName("Test for fail adding of card when txref is not provided")
    public void testForFailAddingOfCard() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add/card")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @DisplayName("Test for success adding of card")
    public void testForSuccessAddingOfCard() throws Exception{

        CardRequest request = new CardRequest("refGODirect-1590768240138");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add/card")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("A new card")));

    }

    @Test
    @DisplayName("Test for failure adding of card when ref is wrong")
    public void testForFailAddingOfCardInvalidRef() throws Exception{

        CardRequest request = new CardRequest("refGODirect-xxxxxxxxx");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add/card")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }

    @Test
    @DisplayName("Testing For Failure when card ID is not passed for charge token")
    public void testForFailWhenCardIDIsNullChargeToken() throws Exception{

        ChargeTokenRequest request = new ChargeTokenRequest();

        request.setAmount(300);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/charge/token")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardId", Matchers.is("cardId must not be empty")));

    }

    @Test
    @DisplayName("Test for success charging with charge request")
    public void testForSuccessChargeWIthToken() throws Exception{


        User user = userService.findUserByUsername("YunusDev");

         Integer preWalletBal = user.getWalletBalance();

        System.out.println("user before loading " + user.getWalletBalance());

        Card card = new Card("flw-t1nf-6c9856cadcd7cbfb88a9fcb53aa76511-m03k"
                , 560, "refGODirect-1590768240138", " CREDIT", "MASTERCARD", "NIGERIA NG", 12, 34, 2950, new Date(), user);

        cardService.save(card);

        Integer amount = 2000;

        ChargeTokenRequest request = new ChargeTokenRequest(card.getId(), amount);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/charge/token")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey())
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        User userUpdated = userService.findUserByUsername("YunusDev");

        Integer afterWalletBal = userUpdated.getWalletBalance();

        Assertions.assertEquals(preWalletBal + amount, afterWalletBal);

        System.out.println("user after loading " + userUpdated.getWalletBalance());


    }

//    **************************************************************************************

    @Test
    @DisplayName("Testing For success getting of users cards")
    public void testForSuccessGettingOfCards() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/cards")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    //    **************************************************************************************

    @Test
    @DisplayName("Testing For success deletion of card")
    public void testForSuccessRemovingOfCard() throws Exception{

        User user = userService.findUserByUsername("YunusDev");

        Card card = new Card("flw-t1nf-6c9856cadcd7cbfb88a9fcb53aa76511-m03k"
                , 560, "refGODirect-1590768240138", " CREDIT", "MASTERCARD", "NIGERIA NG", 12, 34, 2950, new Date(), user);

        cardService.save(card);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/remove/card/{card_id}", card.getId())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Testing For failure deletion of card")
    public void testForFailureRemovingOfCard() throws Exception{

        int cardId = 10;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/remove/card/{card_id}", cardId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("There is no card with id " + cardId)));

    }


}
