package com.vyapaar.vyapaarservice.controller;

import com.vyapaar.vyapaarservice.domain.Bill;
import com.vyapaar.vyapaarservice.domain.Product;
import com.vyapaar.vyapaarservice.domain.User;
import com.vyapaar.vyapaarservice.exception.*;
import com.vyapaar.vyapaarservice.service.BillService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vyapaar")
public class BillController {

    private final BillService billService;
    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/Register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User user1 = billService.registerUser(user);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (UserAlreadyExistException exception) {
            return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/user/addproduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> productList = billService.addProduct(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (UserNotFoundException userNotFound) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductAlreadyExistException productAlreadyExistException) {
            return new ResponseEntity<>(productAlreadyExistException.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/getallproduct")
    public ResponseEntity<?> getAllProduct(HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> productList = billService.getAllProduct(emailId);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getproductbycategory/{productCategory}")
    public ResponseEntity<?> getProductByCategory(HttpServletRequest httpServletRequest, @PathVariable String productCategory) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> userProductList = billService.getProductByCategory(emailId, productCategory);
            return new ResponseEntity<>(userProductList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getproductbystatus/{status}")
    public ResponseEntity<?> getProductByStatus(HttpServletRequest httpServletRequest, @PathVariable boolean status) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> userProductList = billService.getProductByStatus(emailId, status);
            return new ResponseEntity<>(userProductList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getproductbyname/{productName}")
    public ResponseEntity<?> getProductByProductName(HttpServletRequest httpServletRequest, @PathVariable String productName) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> userProductList = billService.getProductByName(emailId, productName);
            return new ResponseEntity<>(userProductList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/getproductbyId/{productId}")
    public ResponseEntity<?> getProductByProductId(HttpServletRequest httpServletRequest, @PathVariable String productId) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> userProductList = billService.getProductByProductId(emailId, productId);
            return new ResponseEntity<>(userProductList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getProductByExpiryDate/{expiryDate}")
    public ResponseEntity<?> getProductByExpireDate(HttpServletRequest httpServletRequest, @PathVariable String expiryDate) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> userProductList = billService.getExpiredProducts(emailId, expiryDate);
            return new ResponseEntity<>(userProductList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getProductByLocation/{storageLocation}")
    public ResponseEntity<?> getProductByLocation(HttpServletRequest httpServletRequest, @PathVariable String storageLocation) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> userProductList = billService.getProductByLocation(emailId, storageLocation);
            return new ResponseEntity<>(userProductList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getProductByBatchNo/{batchNo}")
    public ResponseEntity<?> getProductByBatchNo(HttpServletRequest httpServletRequest, @PathVariable String batchNo) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> userProductList = billService.getProductByBatchNo(emailId, batchNo);
            return new ResponseEntity<>(userProductList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getProductByTax/{tax}")
    public ResponseEntity<?> getProductByTax(HttpServletRequest httpServletRequest, @PathVariable double tax) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Product> userProductList = billService.getProductByTax(emailId, tax);
            return new ResponseEntity<>(userProductList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/deleteallproducts")
    public ResponseEntity<?> deleteAllProduct(HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            User user = billService.deleteAllProduct(emailId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/user/deleteproductbyid/{productId}")
    public ResponseEntity<?> deleteProductById(HttpServletRequest httpServletRequest, @PathVariable String productId) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            User user = billService.deleteProductById(emailId, productId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/user/deleteProductExpiryDate/{expiryDate}")
    public ResponseEntity<?> deleteProductExpiryDate(HttpServletRequest httpServletRequest, @PathVariable String expiryDate) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            User user = billService.deleteProductByExpiryDate(emailId, expiryDate);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/user/deleteProductByBatchNo/{batchNo}")
    public ResponseEntity<?> deleteProductByBatchNo(HttpServletRequest httpServletRequest, @PathVariable String batchNo) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            User user = billService.deleteProductByBatchNo(emailId, batchNo);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/updateproduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();

        try {
            List<Product> productList = billService.updateProduct(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("product Not Matching", HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/user/updatestatus")
    public ResponseEntity<?> updateProductStatus(@RequestBody Product product, HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();

        try {
            List<Product> productList = billService.updateProductAvailableStatus(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("product Not Matching", HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/user/updateprice")
    public ResponseEntity<?> updateProductPrice(@RequestBody Product product, HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();

        try {
            List<Product> productList = billService.updateProductPrice(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("product Not Matching", HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/user/updatestock")
    public ResponseEntity<?> updateProductStock(@RequestBody Product product, HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();

        try {
            List<Product> productList = billService.updateProductQuantity(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("product Not Matching", HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/user/updateProductBatchNo")
    public ResponseEntity<?> updateProductBatchNo(@RequestBody Product product, HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();

        try {
            List<Product> productList = billService.updateProductBatchNo(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("product Not Matching", HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/user/updateProductTax")
    public ResponseEntity<?> updateProductTax(@RequestBody Product product, HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();

        try {
            List<Product> productList = billService.updateProductTax(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("product Not Matching", HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/user/updateProductExpiryDate")
    public ResponseEntity<?> updateProductExpiryDate(@RequestBody Product product, HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();

        try {
            List<Product> productList = billService.updateProductExpiryDate(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("product Not Matching", HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/user/updateProductStorageLocation")
    public ResponseEntity<?> updateProductStorageLocation(@RequestBody Product product, HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();

        try {
            List<Product> productList = billService.updateProductStorageLocation(emailId, product).getProductList();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("product Not Matching", HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/user/createbill")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Bill> billList = billService.createBill(emailId, bill).getBillList();
            return new ResponseEntity<>(billList, HttpStatus.OK);
        } catch (UserNotFoundException userNotFound) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (BillAlreadyExistException billAlreadyExistException) {
            return new ResponseEntity<>(billAlreadyExistException.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/getallbill")
    public ResponseEntity<?> getAllBill(HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Bill> billList = billService.getAllBill(emailId);
            return new ResponseEntity<>(billList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (BillNotFoundException billNotFoundException) {
            return new ResponseEntity<>("Bill Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getbillbyId/{billId}")
    public ResponseEntity<?> getProductByBillId(HttpServletRequest httpServletRequest, @PathVariable String billId) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Bill> userBillList = billService.getBillByBillId(emailId, billId);
            return new ResponseEntity<>(userBillList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (BillNotFoundException billNotFoundException) {
            return new ResponseEntity<>(" Bill Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getbillbyname/{customerName}")
    public ResponseEntity<?> getBillByCustomerName(HttpServletRequest httpServletRequest,@PathVariable String customerName) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Bill> billList = billService.getBillByCustomerName(emailId,customerName);
            return new ResponseEntity<>(billList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (BillNotFoundException billNotFoundException) {
            return new ResponseEntity<>("Bill Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/getbillbydate/{billDate}")
    public ResponseEntity<?> getProductByBillDate(HttpServletRequest httpServletRequest, @PathVariable String billDate) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Bill> userBillList = billService.getBillsByDate(emailId, billDate);
            return new ResponseEntity<>(userBillList, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (BillNotFoundException billNotFoundException) {
            return new ResponseEntity<>(" Bill Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/getshopname")
    public ResponseEntity<?> getShopName(HttpServletRequest httpServletRequest) {

        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            return new ResponseEntity<>(billService.getShopName(emailId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }



}
