package com.yunus.ecommerce_api.controller.admin;
import com.yunus.ecommerce_api.entity.Category;
import com.yunus.ecommerce_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> index() throws Exception{

        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);

    }

    @PostMapping("/categories")
    public ResponseEntity<?> store(@RequestBody Category category ) throws Exception{

        categoryService.save(category);

       return ResponseEntity.created(new URI("/api/admin/categories")).body(category);

    }

    @GetMapping("/categories/{slug}")
    public ResponseEntity<?> show(@PathVariable("slug") String slug) throws  Exception{


        Category category = categoryService.findBySlug(slug);

        System.out.println(">>>>>>>>> " + category);

        if (category == null){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(category);

    }






}
