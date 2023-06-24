import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {HttpClientModule} from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CartStatusComponent } from './components/cart-status/cart-status.component';

import { ProductListComponent } from './commponents/product-list/product-list.component';
import { ProductService } from './services/product.service';
import { Routes, RouterModule } from '@angular/router';
import { ProductCategoryMenuComponent } from './components/product-category-menu/product-category-menu.component';
import { ProductDeatailsComponent } from './components/product-deatails/product-deatails.component';
import { SearchComponent } from './components/search/search.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component'; 
import { ReactiveFormsModule ,FormsModule} from '@angular/forms';
import { ComponentsComponent } from './components/components.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { ProductFormComponent } from './components/product-form/product-form.component';







  const routes: Routes = [
    { path: 'checkout', component: CheckoutComponent },
    { path: 'cart-details', component: CartDetailsComponent },
    { path: 'search/:keyword', component: ProductListComponent },
    { path: 'products/:id', component: ProductDeatailsComponent },
    { path: 'category/:id', component: ProductListComponent },
    { path: 'category', component: ProductListComponent },
    { path: 'products', component: ProductListComponent },
    { path: '', redirectTo: '/product', pathMatch: 'full' },
    { path: '**', redirectTo: '/product', pathMatch: 'full' },
  ];
  



  @NgModule({
    declarations: [
      AppComponent,
      ProductListComponent,
      ProductCategoryMenuComponent,
      ProductCategoryMenuComponent,
      SearchComponent,
      ProductDeatailsComponent,
      CartStatusComponent,
      CartDetailsComponent,
      CheckoutComponent,
      ComponentsComponent,
      RegistrationComponent,
      LoginComponent,
      ProductFormComponent,
   
    
    ],
    imports: [
      RouterModule.forRoot(routes),
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      ReactiveFormsModule,
      FormsModule
    ],
    providers: [ProductService],
    bootstrap: [AppComponent]
  })
  export class AppModule { }
  