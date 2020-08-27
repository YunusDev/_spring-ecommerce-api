package com.yunus.ecommerce_api.dao;


import com.yunus.ecommerce_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products LIMIT 1",  nativeQuery = true)
    public Product fetchOne();


}
