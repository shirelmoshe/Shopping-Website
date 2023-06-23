import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { CartItem } from '../common/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {
 
  cartItems: CartItem[] = [];
  totalPrice:Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity:Subject<number> = new BehaviorSubject<number>(0);


  constructor() { }
  addToCart(theCartItem: CartItem) {
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem | undefined;
  
    if (this.cartItems.length > 0) {
      

      existingCartItem = this.cartItems.find(tempCartItem =>tempCartItem.product_id === theCartItem.product_id);

      alreadyExistsInCart = (existingCartItem !== undefined);
    }
  
    if (alreadyExistsInCart) {
      existingCartItem!.quantity++;
      // Increment the quantity or perform any other logic for an already existing item
    } else {
      // Add theCartItem to the cartItems array
      this.cartItems.push(theCartItem);
    }
    this.computeCartTotals();
  }
  computeCartTotals() {
    let totalPriceValue:number = 0;
    let totalQuantityValue:number=0;
    for(let currentCartItem of this.cartItems){
      totalPriceValue += currentCartItem.quantity * currentCartItem.price;
      totalQuantityValue += currentCartItem.quantity;
    }
  this.totalPrice.next(totalPriceValue);
  this.totalQuantity.next(totalQuantityValue);


  this.logCartData(totalPriceValue,totalQuantityValue);
  }
  logCartData(totalPriceValue: number, totalQuantityValue: number) {
  for(let tempCartItem of this.cartItems){
    const subTotalPrice = tempCartItem.quantity * tempCartItem.price;

  }
  }
  decrementQuntity(theCartItem: CartItem) {
   theCartItem.quantity--;
   if(theCartItem.quantity === 0){
    this.remove(theCartItem);
   }
   else{
    this.computeCartTotals()
   }
 
  }
  remove(theCartItem: CartItem) {
   
    const itemIndex = this.cartItems.findIndex(tempCartItem => tempCartItem.product_id ===theCartItem.product_id);
    if(itemIndex > -1){
      this.cartItems.splice(itemIndex,1);
      this.computeCartTotals();
    }
  }

  
}
