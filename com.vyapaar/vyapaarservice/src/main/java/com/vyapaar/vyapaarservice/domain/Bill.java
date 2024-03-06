package com.vyapaar.vyapaarservice.domain;


import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

public class Bill {
    @MongoId
    private String billId;
    private String customerName;
    private String customerPhoneNo;
    private String customerEmailID;
    private String customerAddress;
    private String billDate;
    private double totalBill;
    private String payMode;
    private double discount;
    List<Product> purchasedProductList; 

    public Bill() {
    }

    public Bill(String billId, String customerName, String customerPhoneNo, String customerEmailID, String customerAddress, String billDate, double totalBill, String payMode, double discount, List<Product> purchasedProductList) {
        this.billId = billId;
        this.customerName = customerName;
        this.customerPhoneNo = customerPhoneNo;
        this.customerEmailID = customerEmailID;
        this.customerAddress = customerAddress;
        this.billDate = billDate;
        this.totalBill = totalBill;
        this.payMode = payMode;
        this.discount = discount;
        this.purchasedProductList = purchasedProductList;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<Product> getPurchasedProductList() {
        return purchasedProductList;
    }

    public void setPurchasedProductList(List<Product> purchasedProductList) {
        this.purchasedProductList = purchasedProductList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }

    public String getCustomerEmailID() {
        return customerEmailID;
    }

    public void setCustomerEmailID(String customerEmailID) {
        this.customerEmailID = customerEmailID;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Double.compare(bill.totalBill, totalBill) == 0 && Double.compare(bill.discount, discount) == 0 && Objects.equals(billId, bill.billId) && Objects.equals(customerName, bill.customerName) && Objects.equals(customerPhoneNo, bill.customerPhoneNo) && Objects.equals(customerEmailID, bill.customerEmailID) && Objects.equals(customerAddress, bill.customerAddress) && Objects.equals(billDate, bill.billDate) && Objects.equals(payMode, bill.payMode) && Objects.equals(purchasedProductList, bill.purchasedProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, customerName, customerPhoneNo, customerEmailID, customerAddress, billDate, totalBill, payMode, discount, purchasedProductList);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNo='" + customerPhoneNo + '\'' +
                ", customerEmailID='" + customerEmailID + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", billDate='" + billDate + '\'' +
                ", totalBill=" + totalBill +
                ", payMode='" + payMode + '\'' +
                ", discount=" + discount +
                ", purchasedProductList=" + purchasedProductList +
                '}';
    }
}
