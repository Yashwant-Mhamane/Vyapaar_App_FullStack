package com.vyapaar.vyapaarservice.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;


@Data
public class Product {
    @MongoId
    private String productId;
    private String productName;
    private double productPrice;
    private int purchasedQty;
    private double productWisePurchasedAmount;
    private int stock;
    private String productCategory;
    private Boolean status;
    private String expiryDate;
    private String storageLocation;
    private String batchNo;
    private double tax;
    private double weight;
    private String color;

}
