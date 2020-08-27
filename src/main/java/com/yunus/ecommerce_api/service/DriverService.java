package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    public List<Driver> findAll();
    public Optional<Driver> findById(Long id);
    public void save(Driver driver);
    public void deleteById(Long id);

}
