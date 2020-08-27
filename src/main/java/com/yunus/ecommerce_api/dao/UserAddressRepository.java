package com.yunus.ecommerce_api.dao;

import com.yunus.ecommerce_api.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    @Query(value = "SELECT * FROM user_addresses WHERE token = :token AND is_verified = 0", nativeQuery = true)
    public UserAddress findByToken(@Param("token") String token);

    @Query(value = "SELECT * FROM user_addresses WHERE is_verified = 1 AND user_id = :user_id", nativeQuery = true)
    public List<UserAddress> findUserVerifiedAddress(@Param("user_id") Long user_id);

    @Query(value = "SELECT * FROM user_addresses WHERE is_verified = 0 AND user_id = :user_id", nativeQuery = true)
    public List<UserAddress> findUserNonVerifiedAddress(@Param("user_id") Long user_id);

}
