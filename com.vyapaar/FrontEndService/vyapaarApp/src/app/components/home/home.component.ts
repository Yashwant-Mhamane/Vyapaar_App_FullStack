import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, map, shareReplay } from 'rxjs';
import { UserServiceService } from 'src/app/service/user-service.service';
import { VyapaarServiceService } from 'src/app/service/vyapaar-service.service';
import { USER } from 'src/model/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{
  isHandset$: Observable<boolean> = this.breakpointObserver
  .observe(Breakpoints.Handset)
  .pipe(
    map((result) => result.matches),
    shareReplay()
  );

constructor(
  private breakpointObserver: BreakpointObserver,
  public service: UserServiceService,
  private router: Router,
  private vyapar: VyapaarServiceService
) {}
isScreenLoaded = false;

// ngOnInit(): void {
//   this.getUserName();
// }

userdetails: USER = {
  emailId: '',
  password: '',
  shopName: '',
  shopLogo: '',
  gstNo:'',
  shopAddress:'',
  phNo:'',
  otp:'',
  billList:[],
  productList:[],
};

// getUserName() {
//   this.vyapar.getShopName().subscribe(
//     (data: USER) => {
//       this.userdetails = data;
//       console.log(this.userdetails);
//       window.onload;
//     },
//     (err: any) => {
//       console.log(err);
//     }
//   );
// }

tooltipText = 'logout';
logout() {
  this.service.isLoggedinUser = false;
  this.service.logOutUser();
  this.router.navigateByUrl('login');
  location.reload();
}

myMargin: string = '10px';

changeMargin1: boolean = true;

changeMargin() {
  if (this.changeMargin1) {
    this.myMargin = '200px'; // new margin value
    this.changeMargin1 = false;
  } else {
    this.myMargin = '10px'; // new margin value
    this.changeMargin1 = true;
  }
}
}
