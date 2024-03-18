package com.vyapaar.vyapaarservice.service;

import com.vyapaar.vyapaarservice.domain.Bill;
import com.vyapaar.vyapaarservice.domain.Product;
import com.vyapaar.vyapaarservice.domain.User;
import com.vyapaar.vyapaarservice.exception.*;

import java.util.List;

public interface BillService {

    User registerUser(User user) throws UserAlreadyExistException;
    User addProduct(String emailId, Product product) throws ProductAlreadyExistException, UserNotFoundException;
    List<Product> getAllProduct(String emailId) throws ProductNotFoundException, UserNotFoundException;
    List<Product> getProductByCategory(String emailId, String priority) throws ProductNotFoundException, UserNotFoundException;

    List<Product> getProductByStatus(String emailId, Boolean status) throws ProductNotFoundException, UserNotFoundException;
    List<Product> getProductByName(String emailId, String productName) throws ProductNotFoundException, UserNotFoundException;
    List<Product> getProductByProductId(String emailId, String productId) throws ProductNotFoundException, UserNotFoundException;
    List<Product> getExpiredProducts(String emailId, String expiryDate) throws ProductNotFoundException, UserNotFoundException;
    List<Product> getProductByLocation(String emailId, String storageLocation) throws ProductNotFoundException, UserNotFoundException;
    List<Product> getProductByBatchNo(String emailId, String batchNo) throws ProductNotFoundException, UserNotFoundException;
    List<Product> getProductByTax(String emailId, double tax) throws ProductNotFoundException, UserNotFoundException;
    //User deleteAllProduct(String emailId) throws ProductNotFoundException, UserNotFoundException;
   User addProductCategorytoList(String emailId,String productCategory) throws UserNotFoundException,ProductCategoryAlreadyExistsException;
    List<String> getProductCategoryList(String emailId) throws UserNotFoundException, ProductNotFoundException;
    User deleteAllProduct(String emailId) throws ProductNotFoundException, UserNotFoundException;
    User deleteProductById(String emailId, String productId) throws ProductNotFoundException, UserNotFoundException;
    User deleteProductByExpiryDate(String emailId, String expiryDate) throws ProductNotFoundException, UserNotFoundException;
    User deleteProductByBatchNo(String emailId, String batchNo) throws ProductNotFoundException, UserNotFoundException;
    User updateProduct(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException;
    User updateProductAvailableStatus(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException;
    User updateProductPrice(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException;
    User updateProductQuantity(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException;
    User updateProductBatchNo(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException;
    User updateProductTax(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException;
    User updateProductExpiryDate(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException;
    User updateProductStorageLocation(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException;

    User createBill(String emailId, Bill bill) throws BillAlreadyExistException, UserNotFoundException;
    List<Bill> getAllBill(String emailId) throws BillNotFoundException, UserNotFoundException;
    List<Bill> getBillByBillId(String emailId, String billId) throws BillNotFoundException, UserNotFoundException;

    List<Bill> getBillByCustomerName(String emailId, String customerName) throws BillNotFoundException, UserNotFoundException;
    List<Bill> getBillsByDate(String emailId, String date) throws BillNotFoundException, UserNotFoundException;
    User deleteAllBill(String emailId) throws BillNotFoundException, UserNotFoundException;
    User deleteBillById(String emailId, String billId) throws BillNotFoundException, UserNotFoundException;
    User getShopName(String emailId) throws UserNotFoundException;
}
