package com.yunus.ecommerce_api.controller.user;

import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.entity.UserAddress;
import com.yunus.ecommerce_api.request.AddressRequest;
import com.yunus.ecommerce_api.request.AddressTokenRequest;
import com.yunus.ecommerce_api.response.ErrorResponse;
import com.yunus.ecommerce_api.service.UserAddressService;
import com.yunus.ecommerce_api.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/address")
    public ResponseEntity<?> getAddress(Authentication authentication){

        User user = userService.findUserByUsername(authentication.getName());

        List<UserAddress> verifiedAddress = userAddressService.findUserVerifiedAddress(user.getId());
        List<UserAddress> nonVerifiedAddress = userAddressService.findUserNonVerifiedAddress(user.getId());

        HashMap<String, List<UserAddress>> map = new HashMap<>();

        map.put("verified_address", verifiedAddress);
        map.put("non_verified_address", nonVerifiedAddress);

        return ResponseEntity.ok(map);

    }

    @PostMapping("/address")
    public ResponseEntity<?> saveAddress(@RequestBody @Valid AddressRequest request, Authentication authentication){

        User user = userService.findUserByUsername(authentication.getName());

        UserAddress userAddress = new UserAddress();

        userAddress.setName(request.getName());
        String token = RandomString.make(60);
        userAddress.setToken(token);
        userAddress.setVerified(false);
        userAddress.setUser(user);
        userAddress.setDate(new Timestamp(System.currentTimeMillis()));

        userAddressService.save(userAddress);

        try {

            String message = "Pls confirm your address, " + request.getName() +  " with the link : http://localhost:9001/api/confirm/address/" + token;

            sendEmail("yunusabdulqudus1@gmail.com", "Confirm Address", message);

            log.info("Email has been sent");

        }catch (Exception e){

            log.info("Error sending email " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userAddress);

    }

    @PostMapping("/resend/address")
    public ResponseEntity<?> resendEmailAddress(@RequestBody @Valid AddressTokenRequest request, Authentication authentication){

        UserAddress userAddress = userAddressService.findByToken(request.getToken());

        if (userAddress == null){

            String message = "User Address doesn't exist";

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));

        }

        try {

            String message = "Pls your confirm your address with the link : http://localhost:9001/api/confirm/address/" + request.getToken();

            sendEmail("yunusabdulqudus1@gmail.com", "Re - Confirm Address", message);

            log.info("Email has been sent");

        }catch (Exception e){

            log.info("Error sending email " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));

        }

        return  ResponseEntity.ok(userAddress);

    }

    @GetMapping("confirm/address/{token}")
    public String confirmAddress(@PathVariable("token") String token){

        UserAddress userAddress = userAddressService.findByToken(token);

        if (userAddress == null){

            return  "<h3 style='color: red'>Invalid Url</h1>";

        }

        userAddress.setVerified(true);
        userAddress.setToken(null);
        userAddressService.save(userAddress);

        return "<h3 style='color: green'>Your address has been verified successfully;</h1>";

    }

    private void sendEmail(String toEmail, String subject, String message) throws Exception{

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("noreply@example.com");

        javaMailSender.send(mailMessage);
    }

}
