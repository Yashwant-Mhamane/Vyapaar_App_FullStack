import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { USER } from 'src/model/user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private hs: HttpClient) {

   }
   public registerUser(user:USER):Observable<USER>
   {
      return this.hs.post<USER>("http://localhost:8083/vyapaar/Register",user);
   }
   public loginUser(user:USER):Observable<USER>
   {
      return this.hs.post<USER>("http://localhost:8083/userauth/login",user);
   }
   public sendOtp(emailId:string):Observable<string>
   {
      return this.hs.post<string>(`http://localhost:8083/userauth/sender/${emailId}`,'');
   }
   public forgetPassword(user:USER,password:string):Observable<USER>
   {
      return this.hs.put<USER>(`http://localhost:8083/userauth/forget/${password}`,user);
   }
   public login(token:any)
   {
      localStorage.setItem("tokenGenerated",token);
      return true;
   }
   isLoggedinUser:boolean = false;
   public isLoggedin()
   {
      let webToken = localStorage.getItem("tokenGenerated");
      if(webToken=="" || webToken==undefined || webToken==null)
      { this.isLoggedinUser=false;
        return false;
      }else
      {this.isLoggedinUser=true;
        return true;
      }
   }
   public logOutUser()
   {
     localStorage.clear();

   }
}
