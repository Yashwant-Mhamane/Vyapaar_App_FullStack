import { Component } from '@angular/core';
import { VyapaarServiceService } from '../service/vyapaar-service.service';

@Component({
  selector: 'app-new-category',
  templateUrl: './new-category.component.html',
  styleUrls: ['./new-category.component.css']
})
export class NewCategoryComponent {
  constructor(private vs : VyapaarServiceService){

  }
  category:string|undefined;
  sumbitCategory(){
    console.log(this.category);
    console.log();
  }
}
