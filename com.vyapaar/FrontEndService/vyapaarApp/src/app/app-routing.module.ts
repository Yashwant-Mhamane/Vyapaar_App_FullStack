import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';
import { EditProductComponent } from './components/edit-product/edit-product.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { CreateBillComponent } from './components/create-bill/create-bill.component';
import { ForgetPasswordComponent } from './components/forget-password/forget-password.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { ViewBillComponent } from './components/view-bill/view-bill.component';
import { ViewProductsComponent } from './components/view-products/view-products.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LogInGuardGuard } from './guard/log-in-guard.guard';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './header/header.component';

const routes: Routes = [

  {path:"login" , component:LogInComponent},
  {path: "", redirectTo: 'login', pathMatch:'full'},
  {path:"register",component:RegisterComponent},
  {path:"pgn" , component:PageNotFoundComponent},
  {path:"home",component:HomeComponent},// removed gaurd
  {path:"header",component:HeaderComponent},// removed gaurd
  {path:"editproduct",component:EditProductComponent},// removed gaurd
  {path:"addproduct" , component:AddProductComponent},// removed gaurd
  {path:"createbill",component:CreateBillComponent},//removed gaurd
  {path:"forget" , component:ForgetPasswordComponent},
  {path:"searchbar",component:SearchBarComponent},// removed gaurd
  {path:"viewbill" , component:ViewBillComponent}, // removed gaurd
  {path:"viewproduct",component:ViewProductsComponent}, // removed gaurd
  {path:"**",component:PageNotFoundComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
