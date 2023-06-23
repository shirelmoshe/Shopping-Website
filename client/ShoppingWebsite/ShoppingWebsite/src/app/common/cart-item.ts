import { Product } from "./product";

export class CartItem {
    product_id:number;
    name:string;
    image_url:string;
    price:number;
    quantity: number;



 constructor(product: Product) {
  this.product_id = product.product_id;
  this.name = product.name;
  this.image_url = product.image_url;
  this.price = product.price;
  this.quantity = 1; // Assuming the initial quantity is 1, but you can change it as needed
}

}
