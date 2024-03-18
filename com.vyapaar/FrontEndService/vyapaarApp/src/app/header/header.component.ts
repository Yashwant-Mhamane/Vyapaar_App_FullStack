import { Component, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/user-service.service';
import { VyapaarServiceService } from 'src/app/service/vyapaar-service.service';

import { BILL } from 'src/model/bill';
import { CreateBillComponent } from '../components/create-bill/create-bill.component';
import { AddProductComponent } from '../components/add-product/add-product.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(
    private h: VyapaarServiceService,
    public service: UserServiceService,
    private router: Router,
    public dialog: MatDialog
  ) { }
  ngOnInit(): void {  
    this.getAllBill();
  }

  openDialog() {
    this.dialog.open(CreateBillComponent);
  }
openDialog1() {
  this.dialog.open(AddProductComponent);
  }

  showMessage: boolean = true;
  showDeleteButton1: boolean = false;

  allBill: BILL[] = [];
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

  searched(customerName: string) {
    if (customerName == '') {
      this.getAllBill();
    } else {
      this.h.getBillByCustomerName(customerName).subscribe((data) => {
        this.allBill = data;
      });
    }
  }

  filtered(dateOfBill: string) {
    if (dateOfBill == '') {
      this.getAllBill();
    } else {
      this.h.getBillByBillDate(dateOfBill).subscribe((data) => {
        this.allBill = data;
      });
    }
  }

  // completionStatus(isCompleted: boolean) {
  //   this.h.getTaskByCompletedStatus(isCompleted).subscribe(
  //     (data) => {
  //       this.allBill = data;
  //     },
  //     (err) => {
  //       // alert('Task Not Found');
  //     }
  //   );
  // }

  // deleteAllBill() {
  //   const confirmed = window.confirm(
  //     'Are you sure you want to delete all tasks?'
  //   );
  //   if (confirmed) {
  //     this.h.deleteAllBill().subscribe({
  //       next: (data) => {
  //         alert('All Task Deleted Successfully ');

  //         location.reload();
  //       },
  //       error: (error) => {
  //         alert(error + 'not deleted');
  //         location.reload();
  //       },
  //     });
  //   }
  // }
}
