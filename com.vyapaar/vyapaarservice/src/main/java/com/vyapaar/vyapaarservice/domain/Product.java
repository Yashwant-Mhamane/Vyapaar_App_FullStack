package com.vyapaar.vyapaarservice.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

public class Product {
    @MongoId
    private String productId;
    private String productName;
    private double productPrice;
    private int purchasedQty;
    private int stock;
    private String productCategory;
    private boolean status;
    private String expiryDate;
    private String storageLocation;
    private String batchNo;
    private double tax;
    private double weight;
    private String color;

    public Product() {
    }

    public Product(String productId, String productName, double productPrice,int purchasedQty, int stock, String productCategory, boolean status, String expiryDate, String storageLocation, String batchNo, double tax, double weight, String color) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.purchasedQty = purchasedQty;
        this.stock = stock;
        this.productCategory = productCategory;
        this.status = status;
        this.expiryDate = expiryDate;
        this.storageLocation = storageLocation;
        this.batchNo = batchNo;
        this.tax = tax;
        this.weight = weight;
        this.color = color;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getPurchasedQty() {
        return purchasedQty;
    }

    public void setPurchasedQty(int purchasedQty) {
        this.purchasedQty = purchasedQty;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.productPrice, productPrice) == 0 && purchasedQty == product.purchasedQty && stock == product.stock && status == product.status && Double.compare(product.tax, tax) == 0 && Double.compare(product.weight, weight) == 0 && Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && Objects.equals(productCategory, product.productCategory) && Objects.equals(expiryDate, product.expiryDate) && Objects.equals(storageLocation, product.storageLocation) && Objects.equals(batchNo, product.batchNo) && Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productPrice, purchasedQty, stock, productCategory, status, expiryDate, storageLocation, batchNo, tax, weight, color);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", purchasedQty=" + purchasedQty +
                ", stock=" + stock +
                ", productCategory='" + productCategory + '\'' +
                ", status=" + status +
                ", expiryDate='" + expiryDate + '\'' +
                ", storageLocation='" + storageLocation + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", tax=" + tax +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
