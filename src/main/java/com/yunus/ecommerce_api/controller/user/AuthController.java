package com.yunus.ecommerce_api.controller.user;

import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.request.AuthenticationRequest;
import com.yunus.ecommerce_api.response.AuthenticationResponse;
import com.yunus.ecommerce_api.response.ErrorResponse;
import com.yunus.ecommerce_api.service.UserService;
import com.yunus.ecommerce_api.util.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception {

        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();

//        LOGGER.info("before logging in ");

        List<User> users  = userService.findAll();

        LOGGER.info("users >>>>>>>>>>>>>>>>>>> " + users);


        try {

            LOGGER.info("logging in success .....");

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password)
            );
        }
        catch (BadCredentialsException e) {

            LOGGER.info("logging in errrr .....");

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Incorrect username or password"));
//
//            throw new Exception("Incorrect username or password", e);
//
        }

        LOGGER.info("after  logging in ");


        final UserDetails userDetails = userService
                .loadUserByUsername(userName);

        User user1 = userService.findUserByUsername(userName);

        LOGGER.info("user -------- " + user1);


        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user1));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User authenticationRequest) throws Exception{

        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();

        System.out.println("Request " + authenticationRequest);
        System.out.println("UserName >>>>>>>>" + userName);

        LOGGER.info("before logging in ");

        List<User> users  = userService.findAll();

        System.out.println("users >>>>>>>>>>>>>>>>>>> " + users);

        try {

            User user = new User(authenticationRequest.getName(), userName,
                    authenticationRequest.getEmail(), authenticationRequest.getPhone(), authenticationRequest.getPassword());

            userService.save(user);

        }catch (Exception e){

            throw new Exception("Something went wrong ");

        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);

        }

        final UserDetails userDetails = userService
                .loadUserByUsername(userName);

        User user1 = userService.findUserByUsername(userName);


        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user1));


    }




}
