package com.yunus.ecommerce_api.dao;

import com.yunus.ecommerce_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("from Category where slug = :slug")
    public Category findBySlug(@Param("slug") String slug);


}
