package com.yunus.ecommerce_api.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> welcome(){

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("app", "Ecommerce APP");
        map.put("status", "ok");

        return ResponseEntity.ok(map);

    }
}
