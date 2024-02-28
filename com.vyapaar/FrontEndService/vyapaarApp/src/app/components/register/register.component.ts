import { Component } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm:any = this.fb.group({
    name: ['', [Validators.required,Validators.minLength(4)]],
    phNo: ['', [Validators.required,Validators.pattern("[6789][0-9]{9}")]],
    emailId: ['', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z.-]+\\.[a-z]{2,4}$')]],
    password: ['', [Validators.required,Validators.minLength(8)]],
    profileImg:[''],
    gstNo: ['', [Validators.required,Validators.pattern(/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9]{1}[A-Z0-9]{2}$/)]],
    shopAddress: ['', [Validators.required,Validators.minLength(10)]],
  });

  constructor(private fb: FormBuilder, private nav1: Router,private hs:UserServiceService, private _snackBar: MatSnackBar) {}


  selectedFile: any = File;
  url: string = '../../../assets/img.png';
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
  onSubmit(): void {
 this.registerForm.value.profileImg = this.url;
    this.hs.registerUser(this.registerForm.value).subscribe(data => {
     this._snackBar.open('Congrats, you have Registered Successfully !!', 'success', {
          duration: 5000
});
      this.nav1.navigateByUrl("/login");
    },err => {
      alert("EmailId Already Exists")
      console.log(err);
      this.registerForm.reset();
  } );

  }
}
