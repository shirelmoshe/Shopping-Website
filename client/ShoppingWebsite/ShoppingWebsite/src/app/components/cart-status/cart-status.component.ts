import { CartService } from './../../services/cart.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart-status',
  templateUrl: './cart-status.component.html',
  styleUrls: ['./cart-status.component.css']
})

export class CartStatusComponent implements OnInit {
totalPrice : number = 0.0;
totalQuantity:number= 0;

constructor(private CartService:CartService){

}
 ngOnInit(): void {
     this.updateCartStatus();
     
 }
  updateCartStatus() {
    this.CartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );
    this.CartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

  }
}
