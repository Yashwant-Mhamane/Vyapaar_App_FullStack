import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/user-service.service';
import { VyapaarServiceService } from 'src/app/service/vyapaar-service.service';
import { PRODUCT } from 'src/model/product';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.css']
})
export class ViewProductsComponent implements OnInit {

  panelOpenState = false;

  allProduct:PRODUCT[]=[];
constructor(private product:VyapaarServiceService, private service:UserServiceService, private router:Router){}
  ngOnInit(): void {
   this.getAllProduct();
  }
  showMessage: boolean = true;
  showDeleteButton1: boolean = false;
  getAllProduct() {
    this.product.getAllProduct().subscribe((data) => {
      this.allProduct = data;
      if (!(this.allProduct == null || this.allProduct.length == 0)) {
        this.showMessage = false;
      } else {
        this.showMessage = true;
      }
      if (!(this.allProduct == null || this.allProduct.length == 0)) {
        this.showDeleteButton1 = true;
      } else {
        this.showDeleteButton1 = false;
      }
    });
    console.log(this.allProduct);
  }

logout() {
  this.service.logOutUser();
  this.router.navigateByUrl("login");
}
}
