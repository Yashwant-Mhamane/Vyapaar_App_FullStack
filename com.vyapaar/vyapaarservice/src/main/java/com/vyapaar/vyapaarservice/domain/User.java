package com.vyapaar.vyapaarservice.domain;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

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

    public User() {
    }

    public User(String emailId, String password, String shopName, String phNo, String shopLogo, String gstNo, String shopAddress, List<Product> productList, List<Bill> billList) {
        this.emailId = emailId;
        this.password = password;
        this.shopName = shopName;
        this.phNo = phNo;
        this.shopLogo = shopLogo;
        this.gstNo = gstNo;
        this.shopAddress = shopAddress;
        this.productList = productList;
        this.billList = billList;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailId, user.emailId) && Objects.equals(password, user.password) && Objects.equals(shopName, user.shopName) && Objects.equals(phNo, user.phNo) && Objects.equals(shopLogo, user.shopLogo) && Objects.equals(gstNo, user.gstNo) && Objects.equals(shopAddress, user.shopAddress) && Objects.equals(productList, user.productList) && Objects.equals(billList, user.billList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, password, shopName, phNo, shopLogo, gstNo, shopAddress, productList, billList);
    }

    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", shopName='" + shopName + '\'' +
                ", phNo='" + phNo + '\'' +
                ", shopLogo='" + shopLogo + '\'' +
                ", gstNo='" + gstNo + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", productList=" + productList +
                ", billList=" + billList +
                '}';
    }
}
