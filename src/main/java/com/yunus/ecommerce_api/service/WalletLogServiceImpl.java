package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.dao.WalletLogRepository;
import com.yunus.ecommerce_api.entity.WalletLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletLogServiceImpl implements WalletLogService {

    @Autowired
    private WalletLogRepository walletLogRepository;

    @Override
    public void save(WalletLog walletLog) {

        walletLogRepository.save(walletLog);

    }

    @Override
    public List<WalletLog> findAll() {

        return walletLogRepository.findAll();

    }
}
