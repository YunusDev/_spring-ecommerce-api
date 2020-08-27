package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.dao.CategoryRepository;
import com.yunus.ecommerce_api.entity.Category;
import com.yunus.ecommerce_api.helpers.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        Optional<Category> category =  categoryRepository.findById(id);

        if (category.isEmpty()){
            return null;
        }

        return category.get();

    }

    @Override
    public Category findBySlug(String slug) {

        Category category;

        try{

           category = categoryRepository.findBySlug(slug);

        }catch (Exception e){

            category = null;
        }


        return category;
    }

    @Override
    public void save(Category category) {

        category.setDate(new Date());
        category.setSlug(Slugify.toSlug(category.getName()));

        categoryRepository.save(category);

    }

    @Override
    public List<Category> findAll() {

        return categoryRepository.findAll();

    }

    @Override
    public void deleteAll() {

        categoryRepository.deleteAll();

    }

    @Override
    public void deleteById(Long id) {

        categoryRepository.deleteById(id);

    }


}
