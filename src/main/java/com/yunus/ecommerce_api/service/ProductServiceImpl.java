package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.dao.ProductRepository;
import com.yunus.ecommerce_api.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

//    @Override
//    public Category findCategory(Product product) {
//        return product.getCategory();
//    }

    @Override
    public void save(Product product) {

        product.setDate(new Date());
        productRepository.save(product);

    }

    @Override
    public void deleteById(Long id) {

        productRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {

        productRepository.deleteAll();

    }

    @Override
    public Product fetchOne() {
        return productRepository.fetchOne();
    }
}
