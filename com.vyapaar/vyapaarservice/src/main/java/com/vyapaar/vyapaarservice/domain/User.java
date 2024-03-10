package com.vyapaar.vyapaarservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;
@Data
@Document
public class User {

    @MongoId
    private String emailId;
    @Transient
    private String password;
    private String shopName;
    private String phNo;
    private String shopLogo;
    private String gstNo;
    private String shopAddress;
    private List<Product> productList;
    private List<Bill> billList;

}
