package com.yunus.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "charge_token")
    private String chargeToken;

    @Column(name = "bin")
    private Integer bin;

    @Column(name = "ref")
    private String ref;

    @Column(name = "brand")
    private String brand;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "issuing_country")
    private String issuingCountry;

    @Column(name = "exp_month")
    private Integer expMonth;

    @Column(name = "exp_year")
    private Integer expYear;

    @Column(name = "last4")
    private Integer last4;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user_card_back_ref")
    private User user;

    @OneToMany(mappedBy = "card", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "wallet_card_back_ref")
    private List<WalletLog> walletLogs;

    public Card() {
    }

    public Card(String chargeToken, Integer bin, String ref, String brand, String cardType, String issuingCountry, Integer expMonth, Integer expYear, Integer last4, Date date, User user) {
        this.chargeToken = chargeToken;
        this.bin = bin;
        this.ref = ref;
        this.brand = brand;
        this.cardType = cardType;
        this.issuingCountry = issuingCountry;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.last4 = last4;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChargeToken() {
        return chargeToken;
    }

    public void setChargeToken(String chargeToken) {
        this.chargeToken = chargeToken;
    }

    public Integer getBin() {
        return bin;
    }

    public void setBin(Integer bin) {
        this.bin = bin;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public Integer getLast4() {
        return last4;
    }

    public void setLast4(Integer last4) {
        this.last4 = last4;
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

    public List<WalletLog> getWalletLogs() {
        return walletLogs;
    }

    public void setWalletLogs(List<WalletLog> walletLogs) {
        this.walletLogs = walletLogs;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", chargeToken='" + chargeToken + '\'' +
                ", bin=" + bin +
                ", ref='" + ref + '\'' +
                ", brand='" + brand + '\'' +
                ", cardType='" + cardType + '\'' +
                ", issuingCountry='" + issuingCountry + '\'' +
                ", expMonth='" + expMonth + '\'' +
                ", expYear=" + expYear +
                ", last4=" + last4 +
                ", date=" + date +
                ", user=" + user.getName() +
                '}';
    }
}
