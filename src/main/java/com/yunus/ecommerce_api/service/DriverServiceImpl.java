package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.dao.DriverRepository;
import com.yunus.ecommerce_api.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private  DriverRepository driverRepository;

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public void save(Driver driver) {

        driverRepository.save(driver);

    }

    @Override
    public void deleteById(Long id) {

        driverRepository.deleteById(id);

    }
}
