package com.yunus.ecommerce_api.seed;

import com.github.javafaker.Faker;
import com.yunus.ecommerce_api.entity.Driver;
import com.yunus.ecommerce_api.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/driver")
public class DriverSeederController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/seed")
    public ResponseEntity<?> seedDrivers(){

        double[][] driverLoc = {

            {6.53585489,3.51919092},
            {6.45336185,3.42926612}, {6.45845659,3.39786285},
            {6.49875374,3.3748771}, {6.45253746,3.4279537},
            {6.44957696,3.39406391}, {6.43234497,3.43221704},
            {6.43752788,3.40548128}, {6.39934718,3.42169645},
            {6.58927231,3.37999248}, {6.53585489,3.51919092},
            {6.52717216,3.38092213}, {6.58533056,3.45609827},
            {6.5295671,3.29803616}, {6.5305748,3.48830353},
            {6.42260054,3.49156917}, {6.41942366,3.43075346}, {6.53585489,3.51919092}, {6.49194592,3.30597321},
            {6.42653518,3.27948923}, {6.38371541,3.34312127},
            {6.36177034,3.3859592}, {6.44432668,3.2786019},
            {6.44496539,3.36184367}, {6.56975097,3.45049093},
            {6.40142917,3.43097683}, {6.47910588,3.53295803},
            {6.38721142,3.41093303}, {6.59372464,3.39452046},
            {6.47433802,3.31550802}, {6.37246793,3.42305564}, {6.49716481,3.33273629}, {6.5012216,3.34913581},
            {6.46025923,3.40667303}, {6.47229849,3.52155028}, {6.4570908,3.42811088},
            {6.44288431,3.37508097}, {6.56124092,3.33272164},
            {6.46830648,3.43647831}, {6.52214988,3.29382404},
            {6.58019324,3.34878387}, {6.37246793,3.42305564}, {6.44038682,3.51034704},
            {6.36925669,3.35467801}, {6.38939531,3.35520063}, {6.40909866,3.37938716},
            {6.45274498,3.33683067}, {6.55948451,3.45998607}, {6.44391526,3.42441695}, {6.44838852,3.42108008}
        };

        for (int i = 0; i < driverLoc.length; i++){

            double [] loc = driverLoc[i];

            double lat = loc[0];
            double lng = loc[1];

            Faker faker = new Faker();

            Driver driver = new Driver(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.phoneNumber().phoneNumber(),
                         bCryptPasswordEncoder.encode("password"), "available", lat, lng, new Date());

            driverService.save(driver);

        }


        return ResponseEntity.ok("Done");




    }

}
