import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  loginForm: any = this.fb.group({
    emailId: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
  });

  constructor(
    private fb: FormBuilder,
    private log: UserServiceService,
    private nav: Router,
    private _snackBar: MatSnackBar
  ) {}
  ngOnInit(): void {}

  login: any;
  onSubmit(): void {
    this.log.loginUser(this.loginForm.value).subscribe(
      (next) => {
        this.login = next;
        this.log.login(this.login.Token);
        if (this.log.isLoggedin()) {
          this.nav.navigateByUrl('header');

          this._snackBar.open('Successfully LoggedIn !!', 'success', {
            duration: 5000,
          });
        }
      },
      (err) => {
        alert('Try with valid Credentails');
        this.loginForm.reset();
      }
    );
  }
}
