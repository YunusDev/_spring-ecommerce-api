package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> findAll();
    public Optional<Product> findById(Long id);
    public void save(Product product);
    public void deleteById(Long id);
    public void deleteAll();
    public Product fetchOne();

}
