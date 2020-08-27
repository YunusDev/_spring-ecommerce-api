package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.entity.Category;

import java.util.List;

public interface CategoryService  {

    public Category findById(Long id);
    public Category findBySlug(String slug);
    public void save(Category category);
    public List<Category> findAll();
    public void deleteAll();
    public void deleteById(Long id);


}
