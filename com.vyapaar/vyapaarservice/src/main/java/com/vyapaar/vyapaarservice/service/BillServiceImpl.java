package com.vyapaar.vyapaarservice.service;

import com.vyapaar.vyapaarservice.Proxy;
import com.vyapaar.vyapaarservice.domain.Bill;
import com.vyapaar.vyapaarservice.domain.Product;
import com.vyapaar.vyapaarservice.domain.User;
import com.vyapaar.vyapaarservice.exception.*;
import com.vyapaar.vyapaarservice.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final Proxy userproxy;
    @Autowired
    public BillServiceImpl(BillRepository billRepository, Proxy userproxy) {
        this.billRepository = billRepository;
        this.userproxy = userproxy;
    }


    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        if (billRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistException("User Already Exists");
        } else {
            User instanceUser = billRepository.save(user);
            if (!instanceUser.getShopName().isEmpty()) userproxy.registerUser(instanceUser);
            return instanceUser;
        }
    }

    @Override
    public User addProduct(String emailId, Product product) throws ProductAlreadyExistException, UserNotFoundException {
        User user;
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        if (billRepository.findById(emailId).isPresent()) {
            user = billRepository.findById(emailId).get();
            List<Product> productList = user.getProductList();
            if (productList == null) {
                productList = Collections.singletonList(product);
            } else {
                if (productList.stream().filter(data -> data.getProductName().equalsIgnoreCase(product.getProductName())).findAny().isPresent()) {
                    throw new ProductAlreadyExistException("Product Already Exist");
                } else {
                    productList.add(product);
                }
            }
            user.setProductList(productList);

        } else {
            throw new UserNotFoundException("User Not Found Exception");
        }
        return billRepository.save(user);
    }

    @Override
    public List<Product> getAllProduct(String emailId) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                //                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("notification", productList);
//                jsonObject.put("emailId",emailId);
//                TaskDTO taskDto = new TaskDTO();
//                taskDto.setJsonObject(jsonObject);
//                rabbitTemplate.convertAndSend(directExchange.getName(),"task-routing",taskDto);
                return user.getProductList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Product> getProductByCategory(String emailId, String category) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                List<Product> productList= user.getProductList();
                return productList.stream().filter(fun -> fun.getProductCategory().equalsIgnoreCase(category)).toList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Product> getProductByStatus(String emailId, Boolean status) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                List<Product> productList= user.getProductList();
                return productList.stream().filter(fun -> fun.getStatus() == (status)&& !fun.getStatus()).toList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Product> getProductByName(String emailId, String productName) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                List<Product> productList= user.getProductList();
                return productList.stream().filter(fun -> fun.getProductName().equalsIgnoreCase(productName)).toList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Product> getProductByProductId(String emailId, String productId) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                List<Product> productList= user.getProductList();
                return productList.stream().filter(fun -> fun.getProductId().equalsIgnoreCase(productId)).toList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Product> getExpiredProducts(String emailId, String expiryDate) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                List<Product> productList= user.getProductList();
                return productList.stream().filter(fun -> fun.getExpiryDate().equalsIgnoreCase(expiryDate)).toList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Product> getProductByLocation(String emailId, String storageLocation) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                List<Product> productList= user.getProductList();
                return productList.stream().filter(fun -> fun.getStorageLocation().equalsIgnoreCase(storageLocation)).toList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Product> getProductByBatchNo(String emailId, String batchNo) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                List<Product> productList= user.getProductList();
                return productList.stream().filter(fun -> fun.getBatchNo().equalsIgnoreCase(batchNo)).toList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Product> getProductByTax(String emailId, double tax) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getProductList() == null) {
                throw new ProductNotFoundException("Product Not Found.");
            } else {
                List<Product> productList= user.getProductList();
                return productList.stream().filter(fun -> fun.getTax()==(tax)).toList();
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<String> getProductCategoryList(String emailId) throws UserNotFoundException {
        Optional<User> user = billRepository.findById(emailId);
        if (user.isPresent()) {
            User existingUser = user.get();
            List<String> productCategoryList = existingUser.getProductCategoryList();
            if (productCategoryList ==null){
                return null;
            }else {
                return productCategoryList;
            }
        }else {
            throw new UserNotFoundException("User does not exists");
        }
    }

    @Override
    public User deleteAllProduct(String emailId) throws ProductNotFoundException, UserNotFoundException {
        boolean productPresent = false;
        if (billRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException("User Not found");
        }
        User user = billRepository.findById(emailId).get();
        List<Product> productList = user.getProductList();
        productPresent = productList.removeAll(productList);
        if (!productPresent) {
            throw new ProductNotFoundException("Product Not Found");
        }
        user.setProductList(productList);
        return billRepository.save(user);
    }

    @Override
    public User deleteProductById(String emailId, String productId) throws ProductNotFoundException, UserNotFoundException {
        boolean productPresent = false;
        if (billRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException("User Not found");
        }
        User user = billRepository.findById(emailId).get();
        List<Product> productList = user.getProductList();
        productPresent = productList.removeIf(function -> function.getProductId().equals(productId));
        if (!productPresent) {
            throw new ProductNotFoundException("Product Not Found");
        }
        user.setProductList(productList);
        return billRepository.save(user);
    }

    @Override
    public User deleteProductByExpiryDate(String emailId, String expiryDate) throws ProductNotFoundException, UserNotFoundException {
        boolean productPresent = false;
        if (billRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException("User Not found");
        }
        User user = billRepository.findById(emailId).get();
        List<Product> productList = user.getProductList();
        productPresent = productList.removeIf(function -> function.getExpiryDate().equals(expiryDate));
        if (!productPresent) {
            throw new ProductNotFoundException("Product Not Found");
        }
        user.setProductList(productList);
        return billRepository.save(user);
    }

    @Override
    public User deleteProductByBatchNo(String emailId, String batchNo) throws ProductNotFoundException, UserNotFoundException {
        boolean productPresent = false;
        if (billRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException("User Not found");
        }
        User user = billRepository.findById(emailId).get();
        List<Product> productList = user.getProductList();
        productPresent = productList.removeIf(function -> function.getBatchNo().equals(batchNo));
        if (!productPresent) {
            throw new ProductNotFoundException("Product Not Found");
        }
        user.setProductList(productList);
        return billRepository.save(user);
    }

    @Override
    public User updateProduct(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User userObj = billRepository.findById(emailId).get();
            if (userObj.getProductList() == null) throw new ProductNotFoundException("Product Not Found.");
            else {
                List<Product> getAllProduct = userObj.getProductList();
                if (!getAllProduct.stream().filter(function -> function.getProductName().equalsIgnoreCase(product.getProductName())).findAny().isPresent())
                    throw new ProductNotFoundException("Product Not Found.");
                else {
                    Product productToBeUpdated = getAllProduct.stream().filter(fun -> fun.getProductName().equalsIgnoreCase(product.getProductName())).findAny().get();
                    int index = getAllProduct.indexOf(productToBeUpdated);
                    if (product.getProductPrice() >= 0) productToBeUpdated.setProductPrice(product.getProductPrice());
                    if (product.getProductCategory() != null) productToBeUpdated.setProductCategory(product.getProductCategory());
                    if (product.getStock() >= 0) productToBeUpdated.setStock(product.getStock());
                    getAllProduct.set(index, productToBeUpdated);
                    userObj.setProductList(getAllProduct);
                    return billRepository.save(userObj);
                }
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User updateProductAvailableStatus(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user1 = billRepository.findById(emailId).get();
            if (user1.getProductList() == null) throw new ProductNotFoundException("Product Not Found.");
            else {
                List<Product> getAllProduct = user1.getProductList();
                if (!getAllProduct.stream().filter(function -> function.getProductId().equals(product.getProductId())).findAny().isPresent())
                    throw new ProductNotFoundException("Product Not Found.");
                else {
                    Product productToBeUpdated = getAllProduct.stream().filter(fun -> fun.getProductId().equals(product.getProductId())).findAny().get();
                    int index = getAllProduct.indexOf(productToBeUpdated);
                    if (!product.getStatus())
                        productToBeUpdated.setStatus(false);

                    getAllProduct.set(index, productToBeUpdated);
                    user1.setProductList(getAllProduct);
                    return billRepository.save(user1);
                }
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User updateProductPrice(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user1 = billRepository.findById(emailId).get();
            if (user1.getProductList() == null) throw new ProductNotFoundException("Product Not Found.");
            else {
                List<Product> getAllProduct = user1.getProductList();
                if (!getAllProduct.stream().filter(function -> function.getProductId().equals(product.getProductId())).findAny().isPresent())
                    throw new ProductNotFoundException("Product Not Found.");
                else {
                    Product productToBeUpdated = getAllProduct.stream().filter(fun -> fun.getProductId().equals(product.getProductId())).findAny().get();
                    int index = getAllProduct.indexOf(productToBeUpdated);
                    if (product.getProductPrice()>= 0)
                        productToBeUpdated.setProductPrice(product.getProductPrice());

                    getAllProduct.set(index, productToBeUpdated);
                    user1.setProductList(getAllProduct);
                    return billRepository.save(user1);
                }
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User updateProductQuantity(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user1 = billRepository.findById(emailId).get();
            if (user1.getProductList() == null) throw new ProductNotFoundException("Product Not Found.");
            else {
                List<Product> getAllProduct = user1.getProductList();
                if (!getAllProduct.stream().filter(function -> function.getProductId().equals(product.getProductId())).findAny().isPresent())
                    throw new ProductNotFoundException("Product Not Found.");
                else {
                    Product productToBeUpdated = getAllProduct.stream().filter(fun -> fun.getProductId().equals(product.getProductId())).findAny().get();
                    int index = getAllProduct.indexOf(productToBeUpdated);
                    if (product.getStock() >= 0)
                        productToBeUpdated.setStock(product.getStock());

                    getAllProduct.set(index, productToBeUpdated);
                    user1.setProductList(getAllProduct);
                    return billRepository.save(user1);
                }
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User updateProductBatchNo(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user1 = billRepository.findById(emailId).get();
            if (user1.getProductList() == null) throw new ProductNotFoundException("Product Not Found.");
            else {
                List<Product> getAllProduct = user1.getProductList();
                if (!getAllProduct.stream().filter(function -> function.getProductId().equals(product.getProductId())).findAny().isPresent())
                    throw new ProductNotFoundException("Product Not Found.");
                else {
                    Product productToBeUpdated = getAllProduct.stream().filter(fun -> fun.getProductId().equals(product.getProductId())).findAny().get();
                    int index = getAllProduct.indexOf(productToBeUpdated);
                    if (product.getBatchNo() != null)
                        productToBeUpdated.setBatchNo(product.getBatchNo());

                    getAllProduct.set(index, productToBeUpdated);
                    user1.setProductList(getAllProduct);
                    return billRepository.save(user1);
                }
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User updateProductTax(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user1 = billRepository.findById(emailId).get();
            if (user1.getProductList() == null) throw new ProductNotFoundException("Product Not Found.");
            else {
                List<Product> getAllProduct = user1.getProductList();
                if (!getAllProduct.stream().filter(function -> function.getProductId().equals(product.getProductId())).findAny().isPresent())
                    throw new ProductNotFoundException("Product Not Found.");
                else {
                    Product productToBeUpdated = getAllProduct.stream().filter(fun -> fun.getProductId().equals(product.getProductId())).findAny().get();
                    int index = getAllProduct.indexOf(productToBeUpdated);
                    if (product.getTax() >= 0)
                        productToBeUpdated.setTax(product.getTax());

                    getAllProduct.set(index, productToBeUpdated);
                    user1.setProductList(getAllProduct);
                    return billRepository.save(user1);
                }
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User updateProductExpiryDate(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user1 = billRepository.findById(emailId).get();
            if (user1.getProductList() == null) throw new ProductNotFoundException("Product Not Found.");
            else {
                List<Product> getAllProduct = user1.getProductList();
                if (getAllProduct.stream().filter(function -> function.getProductId().equals(product.getProductId())).findAny().isEmpty())
                    throw new ProductNotFoundException("Product Not Found.");
                else {
                    Product productToBeUpdated = getAllProduct.stream().filter(fun -> fun.getProductId().equals(product.getProductId())).findAny().get();
                    int index = getAllProduct.indexOf(productToBeUpdated);
                    if (product.getExpiryDate()!= null)
                        productToBeUpdated.setExpiryDate(product.getExpiryDate());

                    getAllProduct.set(index, productToBeUpdated);
                    user1.setProductList(getAllProduct);
                    return billRepository.save(user1);
                }
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User updateProductStorageLocation(String emailId, Product product) throws ProductNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user1 = billRepository.findById(emailId).get();
            if (user1.getProductList() == null) throw new ProductNotFoundException("Product Not Found.");
            else {
                List<Product> getAllProduct = user1.getProductList();
                if (!getAllProduct.stream().filter(function -> function.getProductId().equals(product.getProductId())).findAny().isPresent())
                    throw new ProductNotFoundException("Product Not Found.");
                else {
                    Product productToBeUpdated = getAllProduct.stream().filter(fun -> fun.getProductId().equals(product.getProductId())).findAny().get();
                    int index = getAllProduct.indexOf(productToBeUpdated);
                    if (product.getStorageLocation() != null)
                        productToBeUpdated.setStorageLocation(product.getStorageLocation());

                    getAllProduct.set(index, productToBeUpdated);
                    user1.setProductList(getAllProduct);
                    return billRepository.save(user1);
                }
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public User createBill(String emailId, Bill bill) throws BillAlreadyExistException, UserNotFoundException {
        User user;
        String billId = UUID.randomUUID().toString();
        bill.setBillId(billId);
        bill.setBillDate(LocalDate.now().toString());
        if (billRepository.findById(emailId).isPresent()) {
            user = billRepository.findById(emailId).get();
            List<Bill> billList = user.getBillList();
            if (billList == null) {
                double total = 0.0;
                List<Product> bill1PurchasedProductList = bill.getPurchasedProductList();
                for (Product product:bill1PurchasedProductList) {
                    total += product.getProductPrice()* product.getPurchasedQty();
                }
                bill.setTotalBill(total);
                billList = Collections.singletonList(bill);
            } else {
                if (billList.stream().filter(data -> data.getBillId().equals(bill.getBillId())).findAny().isPresent()) {
                    throw new BillAlreadyExistException("Bill Already Exist");
                } else {
                    double total = 0.0;
                    List<Product> bill1PurchasedProductList = bill.getPurchasedProductList();
                    for (Product product:bill1PurchasedProductList) {
                        total += product.getProductPrice()* product.getPurchasedQty();
                    }
                    bill.setTotalBill(total);
                    billList.add(bill);
                }
            }
            user.setBillList(billList);
        } else {
            throw new UserNotFoundException("User Not Found Exception");
        }
        return billRepository.save(user);
    }

    @Override
    public List<Bill> getAllBill(String emailId) throws BillNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getBillList() == null) {
                throw new BillNotFoundException("Bill Not Found.");
            } else {
                List<Bill> billList = user.getBillList();
                return billList;
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Bill> getBillByBillId(String emailId, String billId) throws BillNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getBillList() == null) {
                throw new BillNotFoundException("Bill Not Found.");
            } else {
                List<Bill> billList= user.getBillList();
                List<Bill> billListById = billList.stream().filter(fun -> fun.getBillId().equalsIgnoreCase(billId)).toList();
                return billListById;
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Bill> getBillByCustomerName(String emailId, String customerName) throws BillNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getBillList() == null) {
                throw new BillNotFoundException("Bill Not Found.");
            } else {
                List<Bill> billList= user.getBillList();
                List<Bill> billListByName = billList.stream().filter(fun -> fun.getCustomerName().equalsIgnoreCase(customerName)).toList();
                return billListByName;
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }

    @Override
    public List<Bill> getBillsByDate(String emailId, String date) throws BillNotFoundException, UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getBillList() == null) {
                throw new BillNotFoundException("Bill Not Found.");
            } else {
                List<Bill> billList= user.getBillList();
                List<Bill> billListByDate = billList.stream().filter(fun -> fun.getBillDate().equalsIgnoreCase(date)).toList();
                return billListByDate;
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }
    @Override
    public User deleteAllBill(String emailId) throws BillNotFoundException, UserNotFoundException {
        boolean billPresent = false;
        if (billRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException("User Not found");
        }
        User user = billRepository.findById(emailId).get();
        List<Bill> billList = user.getBillList();
        billPresent = billList.removeAll(billList);
        if (!billPresent) {
            throw new BillNotFoundException("Bill Not Found");
        }
        user.setBillList(billList);
        return billRepository.save(user);
    }
    @Override
    public User deleteBillById(String emailId,String billId) throws BillNotFoundException, UserNotFoundException {
        boolean billPresent = false;
        if (billRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException("User Not found");
        }
        User user = billRepository.findById(emailId).get();
        List<Bill> billList = user.getBillList().stream().filter(fun -> fun.getBillId().equalsIgnoreCase(billId)).toList();
        billPresent = billList.remove(billList);
        if (!billPresent) {
            throw new BillNotFoundException("Bill Not Found");
        }
        user.setBillList(billList);
        return billRepository.save(user);
    }
    @Override
    public User getShopName(String emailId) throws UserNotFoundException {
        if (billRepository.findById(emailId).isPresent()) {
            User user = billRepository.findById(emailId).get();
            if (user.getShopName() == null) {
                throw new UserNotFoundException("User Not Found.");
            } else {
                return  user;
            }
        } else {
            throw new UserNotFoundException("User Not Found.");
        }
    }
}
