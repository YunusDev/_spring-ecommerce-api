package com.yunus.ecommerce_api.controller.user;

import com.yunus.ecommerce_api.entity.Category;
import com.yunus.ecommerce_api.entity.Product;
import com.yunus.ecommerce_api.response.ErrorResponse;
import com.yunus.ecommerce_api.service.CategoryService;
import com.yunus.ecommerce_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

//ecommerce_api-0.0.1-SNAPSHOT.jar
@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> welcome(){

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("app", "Ecommerce APP");
        map.put("status", "ok");

        return ResponseEntity.ok(map);

    }

    @GetMapping("/api/products")
    public ResponseEntity<?> getProducts(){

        List<Product> products = productService.findAll();

        return ResponseEntity.ok(products);

    }

    @GetMapping("/api/category/{categoryId}/products")
    public ResponseEntity<?> getProducts(@PathVariable  Long categoryId){

        Category category = categoryService.findById(categoryId);

        if (category == null){

            String errMessage  = "There is no category with id " + categoryId;

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errMessage));

        }

        HashMap<String,Object> map = new HashMap<String, Object>();

        map.put("category", category);
//        map.put("products", category.getProducts());

        return ResponseEntity.ok(map);

    }
}
