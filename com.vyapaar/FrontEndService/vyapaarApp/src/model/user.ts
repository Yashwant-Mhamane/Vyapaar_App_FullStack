import { BILL } from "./bill";
import { PRODUCT } from "./product";

export type USER = {
  emailId: string;
  password: string;
  shopName: string;
  shopLogo: string;
  gstNo: string;
  shopAddress: string;
  phNo: string;
  otp: string;
  billList: BILL[];
  productList: PRODUCT[];
  productCategeoryList: string[]
}
