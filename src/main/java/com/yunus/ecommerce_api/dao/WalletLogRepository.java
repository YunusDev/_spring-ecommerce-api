package com.yunus.ecommerce_api.dao;

import com.yunus.ecommerce_api.entity.WalletLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletLogRepository extends JpaRepository<WalletLog, Long> {



}
