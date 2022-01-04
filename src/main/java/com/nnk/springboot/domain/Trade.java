package com.nnk.springboot.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Getter
@Setter
@ToString
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tradeid")
    private int tradeId;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "buyquantity", nullable = false)
    private Double buyQuantity;

    @Column(name = "sellquantity")
    private Double sellQuantity;

    @Column(name = "buyprice")
    private Double buyPrice;

    @Column(name = "sellprice")
    private Double sellPrice;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "tradedate")
    private Timestamp tradeDate;

    @Column(name = "security")
    private String security;

    @Column(name = "status")
    private String status;

    @Column(name = "trader")
    private String trader;

    @Column(name = "book")
    private String book;

    @Column(name = "creationname")
    private String creationName;

    @CreationTimestamp
    @Column(name = "creationdate", nullable = false, updatable = false)
    private Timestamp creationDate;

    @Column(name = "revisionname")
    private String revisionName;

    @Column(name = "revisiondate")
    private Timestamp revisionDate;

    @Column(name = "dealname")
    private String dealName;

    @Column(name = "dealtype")
    private String dealType;

    @Column(name = "sourcelistid")
    private String sourceListId;

    @Column(name = "side")
    private String side;

    public Trade(String account, String type, Double buyQuantity) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }
}
