package com.yunus.ecommerce_api.controller.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.request.AuthenticationRequest;
import com.yunus.ecommerce_api.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Ignore
public class AuthControllerTest {

    private static final Logger LOGGER = LogManager.getLogger(AuthControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Before
    public void before() {

        User user = new User( "Yunus", "YunusDev", "tayos@gmail.com", "09303884384", "password");

        userService.save(user);

    }

    @After
    public void after() {

        userService.deleteAll();

    }

    @Test
    @DisplayName("Test for successful user registration")
    public void testSuccessRegister() throws Exception {

        userService.deleteAll();

        User user = new User( "tayo", "tayodevss", "tayoss@gmail.com", "09303884384", "password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    @DisplayName("Test for fail register duplicate username and email")
    public void testForFailureRegisterDuplicateUserNameAndEmail() throws Exception {

        User user = new User( "Yunus", "YunusDev", "tayos@gmail.com", "09303884384", "password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("Username is already registered")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("Email address is already registered")));

    }

    @Test
    @DisplayName("Test for successful user login")
    public void testForSuccessLogin() throws Exception{

        AuthenticationRequest request = new AuthenticationRequest("YunusDev", "password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    @DisplayName("Test for failure user login")
    public void testForFailureLogin() throws Exception{

        AuthenticationRequest request = new AuthenticationRequest("YunusDevs", "password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Incorrect username or password")));

    }


}
