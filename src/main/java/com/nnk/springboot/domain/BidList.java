package com.nnk.springboot.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidlistid")
    private Integer BidListId;

    @NonNull
    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "bidquantity", nullable = false)
    private Double bidQuantity;

    @Column(name = "askquantity")
    private Double askQuantity;

    @Column(name = "bid")
    private Double bid;

    @Column(name = "ask")
    private Double ask;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "bidListdate")
    private Timestamp bidListDate;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "security")
    private String security;

    @Column(name = "trader")
    private String trader;

    @Column(name = "Book")
    private String Book;

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

    public BidList(String account_test, String type_test, double v) {
        this.account = account_test;
        this.type = type_test;
        this.bidQuantity = v;
    }
}
