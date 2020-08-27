package com.yunus.ecommerce_api.controller.admin;

import com.yunus.ecommerce_api.entity.Category;
import com.yunus.ecommerce_api.entity.Product;
import com.yunus.ecommerce_api.request.ProductRequest;
import com.yunus.ecommerce_api.service.CategoryService;
import com.yunus.ecommerce_api.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class ProductController {

    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<?> index() throws  Exception{

        List<Product> products = productService.findAll();

        LOGGER.info("products ------------------ " + products );

        return ResponseEntity.ok(products);

    }

    @PostMapping("/products")
    public ResponseEntity<?> save(@RequestBody ProductRequest request) throws Exception{


        Category category = categoryService.findById(request.getCategory_id());

        if (category == null){

            return ResponseEntity.notFound().build();

        }

        System.out.println("Category >>>>>>>>>>>>>>>>>>>>>> " + category);

        Product product = new Product(request.getName(), request.isStatus(), request.getQuantity(),
                request.getPrice(), request.getDescription(), new Date(), category);

        System.out.println(">>>>>>>>>>>>>>>>  product " + product);

//        product.setCategory(category);

        productService.save(product);

        return ResponseEntity.created(new URI("/api/admin/products")).body(product);

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) throws  Exception{

        Optional<Product> product = productService.findById(id);

        System.out.println("Product " + product);

        List<Product> products = productService.findAll();

        LOGGER.info("products ------------------ " + products );

        if (product.isEmpty()){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(product);

    }




}
