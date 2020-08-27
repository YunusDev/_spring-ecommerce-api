package com.yunus.ecommerce_api.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunus.ecommerce_api.entity.Category;
import com.yunus.ecommerce_api.entity.Product;
import com.yunus.ecommerce_api.request.ProductRequest;
import com.yunus.ecommerce_api.service.CategoryService;
import com.yunus.ecommerce_api.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    private static final Logger LOGGER = LogManager.getLogger(ProductControllerTest.class);


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;



    @Before
    public void before(){

        LOGGER.info("adding up >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Category cat1 = new Category( "mobile", "mobile", new Date());
        Category cat2 = new Category( "makeup", "makeup", new Date());

        categoryService.save(cat1);
        categoryService.save(cat2);


        Product product1 = new Product( "iphone 12", true, 14,
                45000, "A very good mobile", new Date(), cat1);

        Product product2 = new Product( "Make up brush", true, 11,
                55000, "A very good brush makup", new Date(), cat2);

        productService.save(product1);
        productService.save(product2);

    }

    @After
    public void cleanup(){

        LOGGER.info("cleaning up >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        productService.deleteAll();
        categoryService.deleteAll();

    }

    @Test
    @DisplayName("Test Get All Products")
    public void getAllProducts() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/products"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("iphone 12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(45000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price", Matchers.is(55000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantity", Matchers.is(11)));

    }

    @Test
    @DisplayName("Test saving of a product")
    public void testSavingOfProduct() throws Exception{

        Category cat = categoryService.findBySlug("mobile");

        ProductRequest req = new ProductRequest("Samsung", true, 23,
                25000, cat.getId(), "A very good mobile samsung");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("Test return Not Found When trying to save a product with an invalid category")
    public void testSaveProductWithInvalidCategory() throws Exception{

        ProductRequest req = new ProductRequest("Samsung", true, 23,
                25000, 1000L, "A very good mobile samsung");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());


    }

    @Test
    @DisplayName("Test Getting of a product by id")
    public void testGettingOfProductWIthId() throws  Exception{

        Product product = productService.fetchOne();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/products/{id}", product.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("iphone 12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(14)));

    }

    @Test
    @DisplayName("Test Getting of a product with invalid id")
    public void testGettingOfProductWIthInvalidId() throws  Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/products/{id}", 1000))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


}
