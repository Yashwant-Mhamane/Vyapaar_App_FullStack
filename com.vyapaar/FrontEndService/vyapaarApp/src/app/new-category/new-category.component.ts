import { Component } from '@angular/core';
import { VyapaarServiceService } from '../service/vyapaar-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-category',
  templateUrl: './new-category.component.html',
  styleUrls: ['./new-category.component.css']
})
export class NewCategoryComponent {
  constructor(private vs : VyapaarServiceService,    private router: Router){

  }
  category:string="";
  sumbitCategory(){
    this.vs.addProductCategoryToList(this.category).subscribe(
      response => {
       // this.router.navigateByUrl('addproduct')
       location.reload();
      },
      error => {
        console.error('Error:', error);
      }
    );
  }
}
