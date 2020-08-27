package com.yunus.ecommerce_api.util;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.yunus.ecommerce_api.entity.Card;
import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.entity.WalletLog;
import com.yunus.ecommerce_api.response.CardResponse;
import com.yunus.ecommerce_api.response.ErrorResponse;
import com.yunus.ecommerce_api.service.CardService;
import com.yunus.ecommerce_api.service.UserService;
import com.yunus.ecommerce_api.service.WalletLogService;
import net.bytebuddy.utility.RandomString;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@Transactional
@RestController
public class Flutter {

    private static final Logger log = LoggerFactory.getLogger(Flutter.class);

    private static String FLUTTER_SEC_KEY;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletLogService walletLogService;

    public Flutter(){


    }

    public ResponseEntity<?> saveCard(String txref, User user) throws UnirestException, SQLException {

        String url = "https://ravesandboxapi.flutterwave.com/flwv3-pug/getpaidx/api/v2/verify";
        String secKey = "FLWSECK_TEST-d075072a8c7700b8c1e05b1b1b023fed-X";

        System.out.println("FLUTTER_SEC_KEY " + FLUTTER_SEC_KEY);

        String json = new JSONObject()
                .put("txref", txref)
                .put("SECKEY", secKey)
                .toString();

        log.info("SAVING CARD");

        try {

            log.info("SAVING CARD .....");


            com.mashape.unirest.http.HttpResponse<JsonNode> response =
                    Unirest.post(url)
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .body(json)
                            .asJson();

            JSONObject myJSON = response.getBody().getObject();

            String status = myJSON.getString("status");

            JSONObject data = myJSON.getJSONObject("data");

            if (status.equals("error")){

                String errMessage = data.getString("message");

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errMessage));

            }

            System.out.println("status " + status);
            System.out.println("data " + data);

            if (status.equals("success") && data.getString("status").equals("successful")){

                Integer chargedAmount = data.getInt("chargedamount");
                Integer amount = data.getInt("amount");

                JSONObject cardData = data.getJSONObject("card");

                Integer expiryMonth = Integer.valueOf(cardData.getString("expirymonth"));
                Integer expiryYear = Integer.valueOf(cardData.getString("expiryyear"));
                Integer cardBIN = Integer.valueOf(cardData.getString("cardBIN"));
                Integer last4digits = Integer.valueOf(cardData.getString("last4digits"));
                String brand = cardData.getString("brand");
                String type = cardData.getString("type");
                String issuingCountry = cardData.getString("issuing_country");

                String token = cardData.getJSONArray("card_tokens").getJSONObject(0).getString("embedtoken");

                System.out.println("user_id " + user.getId());
                log.info("type " + type);
                log.info("last4digits " + last4digits);

                Integer walletBalance = user.getWalletBalance();
                user.setWalletBalance(walletBalance + amount);

                userService.update(user);

                Card card = cardService.findByUserAndTypeAndLast4(user.getId(), type, last4digits);

                log.info("Card >>>>>>>>>>> " + card);

                CardResponse cardResponse  = new CardResponse();

                if (card == null){

                    Card newCard = new Card(token, cardBIN, txref, brand, type, issuingCountry, expiryMonth,
                            expiryYear, last4digits, new Date(), user);

                    cardService.save(newCard);

                    logWalletTransactions(data, newCard, txref, user, true);

                    cardResponse.setError(false);
                    cardResponse.setCard(newCard);
                    cardResponse.setNewCard(true);
                    cardResponse.setMessage("A new card");


                }else{

                    logWalletTransactions(data, card, txref, user, false);

                    cardResponse.setError(false);
                    cardResponse.setCard(card);
                    cardResponse.setNewCard(false);
                    cardResponse.setMessage("An already existing card");

                }


                return ResponseEntity.status(HttpStatus.CREATED).body(cardResponse);

            }



        }catch (UnirestException e){

            log.info("ERROR SAVING CARD .....");

            System.out.println("Error ....... " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));

        }

       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something Went wrong"));
    }


    public ResponseEntity<?> chargeWithToken(Long card_id, Integer amount, User user) throws UnirestException {

        Optional<Card> opCard = cardService.findById(card_id);

        if (opCard.isEmpty()){

            String errMessage  = "There is no card with id " + card_id;

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errMessage));

        }

        Card card = opCard.get();

        if (!card.getUser().getId().equals(user.getId())){

            String errMessage  = "The user " + user.getName() + " doesn't own the card with id " + card.getId();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errMessage));

        }

        String url = "https://ravesandboxapi.flutterwave.com/flwv3-pug/getpaidx/api/tokenized/charge";
        String secKey = "FLWSECK_TEST-d075072a8c7700b8c1e05b1b1b023fed-X";

        String ref = "ref-" + RandomString.make(15);

        String json = new JSONObject()
                .put("txRef", ref)
                .put("SECKEY", secKey)
                .put("currency", "NGN")
                .put("country", "NG")
                .put("email", "kola@gmail.com")
                .put("amount", amount)
                .put("token", card.getChargeToken())
                .toString();

        try {

            log.info("Chanrging card");

            com.mashape.unirest.http.HttpResponse<JsonNode> response =
                    Unirest.post(url)
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .body(json)
                            .asJson();

            JSONObject myJSON = response.getBody().getObject();

            JSONObject data = myJSON.getJSONObject("data");
            String status = myJSON.getString("status");

            log.info("data  >>>>>>>" + myJSON);
            log.info("data  >>>>>>>" + data);

            if (status.equals("error")){

                String errMessage = data.getString("message");
                String code = data.getString("code");

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(errMessage);
                List<String> details = new ArrayList<String>();
                details.add(code);
                errorResponse.setDetails(details);

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

            }

            if (data.has("status")){

                String status_succ = data.getString("status");

                if (status.equals("success") && status_succ.equals("successful")) {

                    String txref = data.getString("txRef");
                    String token = data.getJSONObject("chargeToken").getString("embed_token");
                    Integer amountAdded = data.getInt("amount");

                    logWalletTransactions(data, card, txref, user, false);

                    Integer walletBalance = user.getWalletBalance();
                    user.setWalletBalance(walletBalance + amountAdded);

                    userService.update(user);

                    card.setChargeToken(token);
                    cardService.save(card);

                    CardResponse cardResponse = new CardResponse(false, false, "Subsequent charges", card);

                    return ResponseEntity.ok(cardResponse);

                }

                log.info("error 2 >>>>>>>>>>>>>>>>>>>>>");

            }

            log.info("error 1 >>>>>>>>>>>>>>>>>>>>>");

        }catch (UnirestException e){

            System.out.println("Error ....... " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));

        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something Went wrong"));

    }


    private void logWalletTransactions(JSONObject data, Card card, String txref, User user, Boolean is_first_payment) {


        String flwRef = data.has("flwref") ? data.getString("flwref") : data.getString("flwRef");
        Integer charged_amount = data.has("chargedamount") ? data.getInt("chargedamount") : data.getInt("charged_amount");
        String auth_model = data.has("authmodel") ? data.getString("authmodel") : data.getString("authModelUsed");
        String date = data.has("created") ? data.getString("created") : data.getString("createdAt");
        String raveRef = data.has("raveref") ? data.getString("raveref") : null;
        Integer amount = data.getInt("amount");
        Integer app_fee = data.getInt("appfee");
        String currency = data.getString("currency");
        String type = "credit";

        WalletLog walletLog = new WalletLog(txref, flwRef, raveRef, type, amount, charged_amount,
                true, currency, auth_model, app_fee, new Date(), user, card);

        walletLogService.save(walletLog);


    }




}
