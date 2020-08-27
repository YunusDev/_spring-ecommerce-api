package com.yunus.ecommerce_api.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.yunus.ecommerce_api.entity.Driver;
import com.yunus.ecommerce_api.service.DriverService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ClosestDriver {

    private static final Logger log = LoggerFactory.getLogger(ClosestDriver.class);


    @Autowired
    private DriverService driverService;

    public void getClosestDrivers(Double lat, Double lng, Boolean hardcoded) throws UnirestException, IOException {

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json";

        List<Driver> drivers = driverService.findAll();

        StringBuilder origin = new StringBuilder();

        origin.append(lat).append(",").append(lng);

        StringBuilder dest = new StringBuilder();

        for (Driver driver : drivers) {

            dest.append(driver.getLat()).append(",").append(driver.getLng()).append("|");

        }

        if (hardcoded){

            ObjectMapper objectMapper = new ObjectMapper();

//            List<JSONObject> data = (List<JSONObject>) new FileInputStream("driver_closest.json");

            List<HashMap<String, Object>> closest = objectMapper.readValue(new FileInputStream("driver_closest.json"), List.class);

            log.info(" closest  " + closest);


        }else {

            try{

                com.mashape.unirest.http.HttpResponse<JsonNode> response =
                        Unirest.post(url)
                                .queryString("units", "imperial")
                                .queryString("origins", origin)
                                .queryString("destinations", dest)
                                .queryString("key", "")
                                .asJson();

                JSONObject myJson = response.getBody().getObject();

                JSONArray elements = myJson.getJSONArray("rows").getJSONObject(0).getJSONArray("elements");

                log.info(String.valueOf(elements));

                List<HashMap<String, Object>> driverDetails = new ArrayList<>();

                for (int i = 0; i < drivers.size(); i++){

                    Driver driver = drivers.get(i);

                    JSONObject distance = elements.getJSONObject(i).getJSONObject("distance");
                    JSONObject duration = elements.getJSONObject(i).getJSONObject("duration");
                    int time = elements.getJSONObject(i).getJSONObject("distance").getInt("value");

                    Long driver_id = driver.getId();
                    Double driver_lat = driver.getLat();
                    Double driver_lng = driver.getLng();

                    HashMap<String, Object> map = new HashMap<>();

                    map.put("driver_id", driver_id);
                    map.put("time", time);
                    map.put("lat", driver_lat);
                    map.put("lng", driver_lng);
                    map.put("distance", distance);
                    map.put("duration", duration);
                    map.put("name", driver.getFname() + " " + driver.getLname());
                    map.put("email", driver.getEmail());

                    driverDetails.add(map);

                }

                Collections.sort(driverDetails, new Comparator<HashMap<String, Object>>(){
                    public int compare(HashMap<String, Object> one, HashMap<String, Object> two) {

                        Integer first = (Integer) one.get("time");
                        Integer second = (Integer) two.get("time");

                        if (first > second){

                            return  1;

                        }

                        if (first.equals(second)){

                            return 0;
                        }

                        return -1;
                    }
                });

                List<JSONObject> detailsObject = new ArrayList<>();

                for (HashMap<String, Object> data: driverDetails){

                    detailsObject.add(new JSONObject(data));


                }

                log.info("driver Details " + detailsObject);


            }catch (UnirestException e){

                log.info("error " + e.getMessage());

                throw new UnirestException("Invalid");

            }
        }




    }

}
