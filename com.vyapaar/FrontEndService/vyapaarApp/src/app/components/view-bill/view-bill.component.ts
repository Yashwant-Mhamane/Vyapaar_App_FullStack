import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/user-service.service';
import { VyapaarServiceService } from 'src/app/service/vyapaar-service.service';
import { BILL } from 'src/model/bill';

@Component({
  selector: 'app-view-bill',
  templateUrl: './view-bill.component.html',
  styleUrls: ['./view-bill.component.css']
})
export class ViewBillComponent implements OnInit {

  ngOnInit(): void {

this.getAllBill();
  }

  page: number = 1;
  count: number = 0;
  cardSize: number = 6;
  cardSizes: any = [6,3,9];

  @Input()
  allBill: BILL[] = [];

  constructor(private h: VyapaarServiceService, private service: UserServiceService, private router: Router) { }


  logout() {
    this.service.logOutUser();
    this.router.navigateByUrl("login");
  }
  showMessage: boolean = true;
  showDeleteButton1: boolean = false;
  getAllBill() {
    this.h.getAllBill().subscribe((data) => {
      this.allBill = data;
      if (!(this.allBill == null || this.allBill.length == 0)) {
        this.showMessage = false;
      } else {
        this.showMessage = true;
      }
      if (!(this.allBill == null || this.allBill.length == 0)) {
        this.showDeleteButton1 = true;
      } else {
        this.showDeleteButton1 = false;
      }
    });
    console.log(this.allBill);
  }


}
