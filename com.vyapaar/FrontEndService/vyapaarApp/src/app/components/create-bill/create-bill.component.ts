import { formatDate } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { FormBuilder, Validators, AbstractControl, FormArray } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { Router } from '@angular/router';

import { VyapaarServiceService } from 'src/app/service/vyapaar-service.service';
import { PRODUCT } from 'src/model/product';

@Component({
  selector: 'app-create-bill',
  templateUrl: './create-bill.component.html',
  styleUrls: ['./create-bill.component.css']
})
export class CreateBillComponent {
  selectedFile: any = File;
  url: string = '../../../assets/default.jpg';
  todaydates: string = formatDate(new Date(), 'yyyy-MM-dd', 'en-in');
  productList : PRODUCT[]=[];
 total : number=0;
  constructor(
    private fb: FormBuilder,
    private ms: VyapaarServiceService,
    private router1: Router
  ) {

  }
  @ViewChild(MatAccordion)
  accordion!: MatAccordion;

  billForm: any = this.fb.group({
    customerName: ['', [Validators.required, Validators.minLength(4)]],
    customerPhoneNo: ['', [Validators.required,Validators.pattern("[6789][0-9]{9}")]],
    customerEmailID: ['', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z.-]+\\.[a-z]{2,4}$')]],
    customerAddress: ['', [Validators.required, Validators.minLength(4)]],
    payMode: ['', [Validators.required, Validators.minLength(3)]],
    // totalBill:[0], taking from backend.
    discount: ['', [Validators.required,Validators.min(0)]],
    billDate: [this.todaydates],
    selectedProduct: [''],
    selectedProductQuantity:[''], // for selecting products to add to the bill
    purchasedProductList: this.fb.array([])
  });


  get selectedProductId(): string {
    return this.billForm.get('selectedProduct').value;
  }

  get productListControl(): FormArray {
    return this.billForm.get('purchasedProductList') as FormArray;
  }

  get purchasedQty():number{
    return this.billForm.get('selectedProductQuantity').value;
  }

  addProductToBill(): void {
    const selectedProduct = this.productList.find(product => product.productId === this.selectedProductId);

    if (selectedProduct) {
      this.productListControl.push(this.fb.group({
        productName: selectedProduct.productName,
        purchasedQty: this.purchasedQty, // default quantity
        productPrice: [selectedProduct.productPrice, Validators.required]
      }));
    }
  }

  removeProductFromBill(index: number): void {
    this.productListControl.removeAt(index);
  }

  ngOnInit() {
    this.ms.getAllBill();
    this.ms.getAllProduct().subscribe(products => {
      this.productList = products;
    });
  }

  onFileSelected(file: any) {
    if (file.target.files) {
      const reader = new FileReader();
      reader.readAsDataURL(file.target.files[0]);
      reader.onload = (event: any) => {
        this.url = event.target.result;
      };
    }
    const filedata = file.target.files[0];
  }

  var1: string = '';
  customerName1: any;
  now: Date = new Date();
  year: number = this.now.getFullYear();
  month: number = this.now.getMonth() + 1;
  date: number = this.now.getDate();
  hours: number = this.now.getHours();
  minutes: number = this.now.getMinutes();
  seconds: number = this.now.getSeconds();

  formattedDate: string = `${this.year}-${
    this.month < 10 ? '0' + this.month : this.month
  }-${this.date < 10 ? '0' + this.date : this.date}`;
  formattedTime: string = `${this.hours < 10 ? '0' + this.hours : this.hours}:${
    this.minutes < 10 ? '0' + this.minutes : this.minutes
  }:${this.seconds < 10 ? '0' + this.seconds : this.seconds}`;

  dateTime: string = this.formattedDate.concat(' Time - ', this.formattedTime);
  onSubmit() {

    this.var1 = this.billForm.value.customerName;
    this.customerName1 = this.var1.replace(/\s/g, '');
    this.billForm.value.customerName = this.customerName1;
    // this.productForm.value.imgUrl = this.url;
    this.ms.createBill(this.billForm.value).subscribe(
      (data) => {
       // console.log(data);
        this.router1.navigateByUrl('header');
        alert('Bill Created successfully');
        location.reload();
      },
      (error) => {
        alert('This Bill already exists');
        console.log(error);
      }
    );
  }
}
