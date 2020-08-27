package com.yunus.ecommerce_api.controller.user;

import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.entity.WalletLog;
import com.yunus.ecommerce_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class WalletController {

    @Autowired
    private UserService userService;

    @GetMapping("/wallet/history")
    public ResponseEntity<?> walletHistory(Authentication authentication){

        User user = userService.findUserByUsername(authentication.getName());

        List<WalletLog> logs = user.getWalletLogs();

        return ResponseEntity.ok(logs);


    }




}
