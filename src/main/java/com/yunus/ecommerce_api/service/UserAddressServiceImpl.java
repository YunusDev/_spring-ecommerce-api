package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.dao.UserAddressRepository;
import com.yunus.ecommerce_api.entity.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressServiceImpl implements UserAddressService{

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public Optional<UserAddress> findById(Long id) {
        return userAddressRepository.findById(id);
    }

    @Override
    public UserAddress findByToken(String token) {
        return userAddressRepository.findByToken(token);
    }

    @Override
    public List<UserAddress> findAll() {
        return userAddressRepository.findAll();
    }

    @Override
    public void deleteAll() {
        userAddressRepository.deleteAll();
    }

    @Override
    public List<UserAddress> findUserVerifiedAddress(Long userId) {
        return userAddressRepository.findUserVerifiedAddress(userId);

    }

    @Override
    public List<UserAddress> findUserNonVerifiedAddress(Long userId) {
        return userAddressRepository.findUserNonVerifiedAddress(userId);
    }

    @Override
    public void save(UserAddress userAddress) {

        userAddressRepository.save(userAddress);

    }
}
