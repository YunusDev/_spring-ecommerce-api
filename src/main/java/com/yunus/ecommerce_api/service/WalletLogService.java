package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.entity.WalletLog;

import java.util.List;

public interface WalletLogService {

    public void save(WalletLog walletLog);
    public List<WalletLog> findAll();
}
