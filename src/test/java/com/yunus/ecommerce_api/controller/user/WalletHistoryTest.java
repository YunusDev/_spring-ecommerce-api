package com.yunus.ecommerce_api.controller.user;


import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.service.CardService;
import com.yunus.ecommerce_api.service.UserService;
import com.yunus.ecommerce_api.service.WalletLogService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Ignore
public class WalletHistoryTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Autowired
    private WalletLogService walletLogService;

    @Autowired
    private MockMvc mockMvc;

    private String jwtKey;

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    public String getJwtKey() {
        return jwtKey;
    }

    @Before
    public  void before(){

        User user = new User( "Yunus", "YunusDev",
                "tayos@gmail.com", "09303884384", "password");

        userService.save(user);

        setJwtKey("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJZdW51c0RldiIsImV4cCI6MTU5ODU1N" +
                "zIzNiwiaWF0IjoxNTk4NTIxMjM2fQ.Pzl_5kihmEJrJPlAX2bA0SPUrxhh1SRmZ-xdaFWrBxw");

    }

    @Test
    @DisplayName("Testing For success getting of user walletHistory")
    public void testForSuccessGettingOfUserWalletHistory() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/wallet/history")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtKey()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
