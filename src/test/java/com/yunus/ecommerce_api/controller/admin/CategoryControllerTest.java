package com.yunus.ecommerce_api.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunus.ecommerce_api.entity.Category;
import com.yunus.ecommerce_api.service.CategoryService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
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

import java.util.Date;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EcommerceApiApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    @Before
    public void before(){

        Category cat1 = new Category(1L, "mobile", "mobile", new Date());
        Category cat2 = new Category(2L, "makeup", "makeup", new Date());

        categoryService.save(cat1);
        categoryService.save(cat2);

    }

    @After
    public void cleanup(){

        categoryService.deleteAll();

    }


    @Test
    @DisplayName("Test for Getting all Categories")
    public  void testGetAllCategories() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))


                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("mobile")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("makeup")));

    }

    @Test
    @DisplayName("Test for Creating Categories")
    public void testForCreatingCategories() throws Exception {

        Category category = new Category("dang", "dang", new Date());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }


    @Test
    @DisplayName("Get Category by a valid Slug")
    public void testCategoryByValidSlug() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/categories/{slug}", "makeup"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("makeup")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.slug", Matchers.is("makeup")));

    }

    @Test
    @DisplayName("Get Category by a in valid Slug")
    public void testCategoryByInValidSlug() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/categories/{slug}", "makeuddp"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());


    }



}
