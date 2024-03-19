import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BILL } from 'src/model/bill';
import { PRODUCT } from 'src/model/product';
import { USER } from 'src/model/user';

@Injectable({
  providedIn: 'root'
})
export class VyapaarServiceService {

  constructor(private hs:HttpClient) { }
  public getAllProduct():Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>("http://localhost:8083/vyapaar/user/getallproduct", requestOption);
  }

  public getProductbyCategory(category:string):Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>(`http://localhost:8083/vyapaar/user/getproductbycategory/${category}`, requestOption);
  }
  public getProductByStatus(status:boolean):Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>(`http://localhost:8083/vyapaar/user/getproductbystatus/${status}`, requestOption);
  }
  public getProductByName(productName:string):Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>(`http://localhost:8083/vyapaar/user/getproductbyname/${productName}`, requestOption);
  }
  public getProductByProductId(productId:string):Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>(`http://localhost:8083/vyapaar/user/getproductbyId/${productId}`, requestOption);
  }

  public getProductByExpiryDate(expiryDate:string):Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>(`http://localhost:8083/vyapaar/user/getProductByExpiryDate/${expiryDate}`, requestOption);
  }

  public getProductCategoryList():Observable<[]>{
    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<[]>("http://localhost:8083/vyapaar/user/getProductCategoryList",requestOption);
  }

  public getProductByLocation(storageLocation:string):Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>(`http://localhost:8083/vyapaar/user/getProductByLocation/${storageLocation}`, requestOption);
  }

  public getProductByBatchNo(batchNo:string):Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>(`http://localhost:8083/vyapaar/user/getProductByBatchNo/${batchNo}`, requestOption);
  }

  public getProductByTax(tax:number):Observable<PRODUCT[]>
  {

    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.get<PRODUCT[]>(`http://localhost:8083/vyapaar/user/getProductByTax/${tax}`, requestOption);
  }


  addProduct(product: PRODUCT): Observable<PRODUCT> {
    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.post<PRODUCT>("http://localhost:8083/vyapaar/user/addproduct", product,requestOption);
    }

    addProductCategoryToList(productCategory: String): Observable<USER> {
      let httpHeaders=new HttpHeaders({
        'Content-Type':'application/json',
        Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
      });
      let requestOption= {headers:httpHeaders}
      return this.hs.post<USER>("http://localhost:8083/vyapaar/user/addProductCategory", productCategory,requestOption);
      }


     public deleteProductById(productId:string){
       let httpHeaders=new HttpHeaders({
         'Content-Type':'application/json',
         Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
       });
       let requestOption= {headers:httpHeaders}
       return this.hs.delete(`http://localhost:8083/vyapaar/user/deleteproductbyid/${productId}`, requestOption);
      }

      public deleteProductByExpiryDate(expiryDate:string){
        let httpHeaders=new HttpHeaders({
          'Content-Type':'application/json',
          Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
        });
        let requestOption= {headers:httpHeaders}
        return this.hs.delete(`http://localhost:8083/vyapaar/user/deleteProductExpiryDate/${expiryDate}`, requestOption);
       }

       public deleteProductByBatchNo(batchNo:string){
        let httpHeaders=new HttpHeaders({
          'Content-Type':'application/json',
          Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
        });
        let requestOption= {headers:httpHeaders}
        return this.hs.delete(`http://localhost:8083/vyapaar/user/deleteProductByBatchNo/${batchNo}`, requestOption);
       }

      public updateProduct(product:PRODUCT){
       let httpHeaders=new HttpHeaders({
         'Content-Type':'application/json',
         Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
       });
       let requestOption= {headers:httpHeaders}
       return this.hs.put("http://localhost:8083/vyapaar/user/updateproduct",product, requestOption);
      }
      public updateProductStatus(product:PRODUCT){
       let httpHeaders=new HttpHeaders({
         'Content-Type':'application/json',
         Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
       });
       let requestOption= {headers:httpHeaders}
       return this.hs.put("http://localhost:8083/vyapaar/user/updatestatus",product, requestOption);
      }

      public updateProductPrice(product:PRODUCT){
        let httpHeaders=new HttpHeaders({
          'Content-Type':'application/json',
          Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
        });
        let requestOption= {headers:httpHeaders}
        return this.hs.put("http://localhost:8083/vyapaar/user/updateprice",product, requestOption);
       }
       public updateProductStock(product:PRODUCT){
        let httpHeaders=new HttpHeaders({
          'Content-Type':'application/json',
          Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
        });
        let requestOption= {headers:httpHeaders}
        return this.hs.put("http://localhost:8083/vyapaar/user/updatestock",product, requestOption);
       }
       public updateProductBatchNo(product:PRODUCT){
        let httpHeaders=new HttpHeaders({
          'Content-Type':'application/json',
          Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
        });
        let requestOption= {headers:httpHeaders}
        return this.hs.put("http://localhost:8083/vyapaar/user/updateProductBatchNo",product, requestOption);
       }
       public updateProductTax(product:PRODUCT){
        let httpHeaders=new HttpHeaders({
          'Content-Type':'application/json',
          Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
        });
        let requestOption= {headers:httpHeaders}
        return this.hs.put("http://localhost:8083/vyapaar/user/updateProductTax",product, requestOption);
       }
       public updateProductExpiryDate(product:PRODUCT){
        let httpHeaders=new HttpHeaders({
          'Content-Type':'application/json',
          Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
        });
        let requestOption= {headers:httpHeaders}
        return this.hs.put("http://localhost:8083/vyapaar/user/updateProductExpiryDate",product, requestOption);
       }
       public updateProductStorageLocation(product:PRODUCT){
        let httpHeaders=new HttpHeaders({
          'Content-Type':'application/json',
          Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
        });
        let requestOption= {headers:httpHeaders}
        return this.hs.put("http://localhost:8083/vyapaar/user/updateProductStorageLocation",product, requestOption);
       }

  createBill(bill: BILL): Observable<BILL> {
    let httpHeaders=new HttpHeaders({
      'Content-Type':'application/json',
      Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    });
    let requestOption= {headers:httpHeaders}
    return this.hs.post<BILL>("http://localhost:8083/vyapaar/user/createbill", bill,requestOption);
    }

    public getAllBill():Observable<BILL[]>
    {

      let httpHeaders=new HttpHeaders({
        'Content-Type':'application/json',
        Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
      });
      let requestOption= {headers:httpHeaders}
      return this.hs.get<BILL[]>("http://localhost:8083/vyapaar/user/getallbill", requestOption);
    }

    public getBillByBillId(billId:string):Observable<BILL[]>
    {

      let httpHeaders=new HttpHeaders({
        'Content-Type':'application/json',
        Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
      });
      let requestOption= {headers:httpHeaders}
      return this.hs.get<BILL[]>(`http://localhost:8083/vyapaar/user/getbillbyId/${billId}`, requestOption);
    }


    public getBillByCustomerName(customerName:string):Observable<BILL[]>
    {

      let httpHeaders=new HttpHeaders({
        'Content-Type':'application/json',
        Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
      });
      let requestOption= {headers:httpHeaders}
      return this.hs.get<BILL[]>(`http://localhost:8083/vyapaar/user/getbillbyname/${customerName}`, requestOption);
    }

    public getBillByBillDate(dateOfBill:string):Observable<BILL[]>
    {

      let httpHeaders=new HttpHeaders({
        'Content-Type':'application/json',
        Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
      });
      let requestOption= {headers:httpHeaders}
      return this.hs.get<BILL[]>(`http://localhost:8083/vyapaar/user/getbillbydate/${dateOfBill}`, requestOption);
    }

    // public getShopName():Observable<USER>
    // {

    //   let httpHeaders=new HttpHeaders({
    //     'Content-Type':'application/json',
    //     Authorization :'Bearer '+localStorage.getItem('tokenGenerated')
    //   });
    //   let requestOption= {headers:httpHeaders}
    //   return this.hs.get<USER>("http://localhost:8083/vyapaar/user/getshopname",requestOption);
    // }

}
