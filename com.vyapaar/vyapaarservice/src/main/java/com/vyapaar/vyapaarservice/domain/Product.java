package com.vyapaar.vyapaarservice.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;
import java.util.Objects;

@Data
public class Product {
    @MongoId
    private String productId;
    private String productName;
    private double productPrice;
    private int purchasedQty;
    private int stock;
    private String productCategory;
    private Boolean status;
    private String expiryDate;
    private String storageLocation;
    private String batchNo;
    private String[] productCategoryList;
    private double tax;
    private double weight;
    private String color;

}
