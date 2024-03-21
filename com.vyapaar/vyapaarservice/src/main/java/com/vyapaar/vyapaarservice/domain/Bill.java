package com.vyapaar.vyapaarservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.List;

@AllArgsConstructor
@Data
public class Bill {
    @MongoId
    private String billId;
    private String customerName;
    private String customerPhoneNo;
    private String customerEmailID;
    private String customerAddress;
    private String billDate;
    private double totalBill;
    private double totalBillBeforeDiscount;
    private String payMode;
    private double discount;
    List<Product> purchasedProductList;
}
