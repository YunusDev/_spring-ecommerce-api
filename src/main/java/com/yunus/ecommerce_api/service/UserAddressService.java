package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.entity.UserAddress;

import java.util.List;
import java.util.Optional;

public interface UserAddressService {

    public Optional<UserAddress> findById(Long id);
    public UserAddress findByToken(String token);
    public List<UserAddress> findAll();
    public List<UserAddress> findUserVerifiedAddress(Long userId);
    public List<UserAddress> findUserNonVerifiedAddress(Long userId);
    public void save(UserAddress userAddress);
    public void deleteAll();
}
