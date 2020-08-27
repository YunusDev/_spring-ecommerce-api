package com.yunus.ecommerce_api.controller.user;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.request.CardRequest;
import com.yunus.ecommerce_api.request.ChargeTokenRequest;
import com.yunus.ecommerce_api.response.ErrorResponse;
import com.yunus.ecommerce_api.service.CardService;
import com.yunus.ecommerce_api.service.UserService;
import com.yunus.ecommerce_api.util.Flutter;
import com.yunus.ecommerce_api.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/")
public class CardController extends Flutter {

    private static final Logger log = LoggerFactory.getLogger(Flutter.class);

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

//    private Flutter flutter;

    @PostMapping("/add/card")
    public ResponseEntity<?> addCard(@RequestBody @Valid CardRequest request, Authentication authentication) throws UnirestException, SQLException, URISyntaxException {

        User user = userService.findUserByUsername(authentication.getName());

        String txref = request.getTxref();

        return super.saveCard(txref, user);


    }

    @PostMapping("/charge/token")
    public ResponseEntity<?> chargeToken(@RequestBody @Valid ChargeTokenRequest request, Authentication authentication) throws UnirestException, SQLException {

        User user = userService.findUserByUsername(authentication.getName());

        return  super.chargeWithToken(request.getCardId(), request.getAmount(), user);

//        return ResponseEntity.ok(user);

    }

    @GetMapping("/user/cards")
    public ResponseEntity<?> getCards( Authentication authentication) throws SQLException {

        User user = userService.findUserByUsername(authentication.getName());

        return ResponseEntity.ok(user.getCards());

    }

    @DeleteMapping("/remove/card/{card_id}")
    public ResponseEntity<?> removeCard(@PathVariable("card_id") Long cardId, Authentication authentication) throws SQLException {

        User user = userService.findUserByUsername(authentication.getName());

        try {

            cardService.deleteById(cardId);

        }catch (Exception e){

            String errMessage  = "There is no card with id " + cardId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errMessage));

        }


        return ResponseEntity.ok(user);

    }





}
