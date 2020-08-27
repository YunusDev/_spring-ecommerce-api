package com.yunus.ecommerce_api.controller.user;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.yunus.ecommerce_api.util.ClosestDriver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
public class ClosestDriversController extends ClosestDriver {


    @GetMapping("/closest/driver")
    public ResponseEntity<?> closestDrivers() throws UnirestException, IOException {

        double lat = 6.58927231;
        double lng = 3.37999248;

        super.getClosestDrivers(lat, lng, true);

        return ResponseEntity.ok("Done");


    }

}
