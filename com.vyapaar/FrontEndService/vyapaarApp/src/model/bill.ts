import { PRODUCT } from "./product";

export type BILL =
{
  billId:string;
  customerName?:string;
  customerPhoneNo?:string;
  customerEmailID?:string;
  customerAddress?:string;
  billDate?:string;
  totalBill?:number;
  totalBillBeforeDiscount?:number;
  discount?:boolean;
  payMode?:string;
  purchasedProductList?:PRODUCT[];
}
