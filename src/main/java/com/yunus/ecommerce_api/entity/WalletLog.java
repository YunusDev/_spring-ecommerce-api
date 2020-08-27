package com.yunus.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wallet_logs")
public class WalletLog {

//    public final CREDIT = "credit";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ref")
    private String ref;

    @Column(name = "flw_ref")
    private String flwRef;

    @Column(name = "rave_ref")
    private String raveRef;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "charged_amount")
    private Integer chargedAmount;

    @Column(name = "is_wallet")
    private Boolean isWallet;

    @Column(name = "auth_model")
    private String authModel;

    @Column(name = "app_fee")
    private Integer appFee;

    @Column(name = "currency")
    private String currency;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user_wallet_back_ref")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    @JsonBackReference(value = "wallet_card_back_ref")
    private Card card;

    public WalletLog() {
    }

    public WalletLog(String ref, String flwRef, String raveRef, String type, Integer amount,
                     Integer chargedAmount, Boolean isWallet, String currency, String authModel, Integer appFee, Date date, User user, Card card) {
        this.ref = ref;
        this.flwRef = flwRef;
        this.raveRef = raveRef;
        this.type = type;
        this.amount = amount;
        this.chargedAmount = chargedAmount;
        this.isWallet = isWallet;
        this.currency = currency;
        this.authModel = authModel;
        this.appFee = appFee;
        this.date = date;
        this.user = user;
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getFlwRef() {
        return flwRef;
    }

    public void setFlwRef(String flwRef) {
        this.flwRef = flwRef;
    }

    public String getRaveRef() {
        return raveRef;
    }

    public void setRaveRef(String raveRef) {
        this.raveRef = raveRef;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Integer chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Boolean getWallet() {
        return isWallet;
    }

    public void setWallet(Boolean wallet) {
        isWallet = wallet;
    }

    public String getAuthModel() {
        return authModel;
    }

    public void setAuthModel(String authModel) {
        this.authModel = authModel;
    }

    public Integer getAppFee() {
        return appFee;
    }

    public void setAppFee(Integer appFee) {
        this.appFee = appFee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "WalletLog{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", flwRef='" + flwRef + '\'' +
                ", raveRef='" + raveRef + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", chargedAmount=" + chargedAmount +
                ", isWallet=" + isWallet +
                ", authModel='" + authModel + '\'' +
                ", appFee='" + appFee + '\'' +
                ", date='" + date + '\'' +
//                ", user=" + user.getName() +
//                ", card=" + card.getBrand() +
                '}';
    }
}
