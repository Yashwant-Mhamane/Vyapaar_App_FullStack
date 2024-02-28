import { formatDate } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { Router } from '@angular/router';
import { VyapaarServiceService } from 'src/app/service/vyapaar-service.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  selectedFile: any = File;
  url: string = '../../../assets/default.jpg';
  todaydates: string = formatDate(new Date(), 'yyyy-MM-dd', 'en-in');


  constructor(
    private fb: FormBuilder,
    private ms: VyapaarServiceService,
    private router1: Router
  ) {
    ms.getAllProduct();
  }
  @ViewChild(MatAccordion)
  accordion!: MatAccordion;

  productForm: any = this.fb.group({
    productName: ['', [Validators.required, Validators.minLength(4)]],
    productPrice: ['', [Validators.required, Validators.min(0)]],
    stock: ['', [Validators.required,Validators.min(0)]],
    productCategory: ['', [Validators.required, Validators.minLength(3)]],
    storageLocation: ['', [Validators.required, Validators.minLength(2)]],
    batchNo: ['', [Validators.required, Validators.minLength(2)]],
    tax: ['', [Validators.required,Validators.min(0)]],
    weight: ['', [Validators.required,Validators.min(0)]],
    color: ['', [Validators.required, Validators.minLength(2)]],
    createdDateTime: [this.todaydates],
    expiryDate: ['', [Validators.required, this.getdataValidate]],
    status: [false],
    updatedTask: [''],
  });

  ngOnInit() {
    this.ms.getAllProduct();
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

  get expiryDate() {
    return this.productForm.controls.expiryDate;
  }

  getdataValidate(ac: AbstractControl) {
    let todaydate = formatDate(new Date(), 'yyyy-MM-dd', 'en-in');
    let dateentered = ac.value;

    if (dateentered > todaydate) {
      return null;
    } else if (todaydate > dateentered) {
      return { ErrorData1: true };
    } else {
      return null;
    }
  }
  var1: string = '';
  productName1: any;
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
    this.productForm.value.updatedTask = this.dateTime;
    this.var1 = this.productForm.value.productName;
    this.productName1 = this.var1.replace(/\s/g, '');
    this.productForm.value.productName = this.productName1;
    // this.productForm.value.imgUrl = this.url;
    this.ms.addProduct(this.productForm.value).subscribe(
      (data) => {
        console.log(data);
        this.router1.navigateByUrl('header');
        alert('Product added successfully');
        location.reload();
      },
      (error) => {
        alert('This Product already exists');
        console.log(error);
      }
    );
  }
}
