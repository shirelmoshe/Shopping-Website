import { CartService } from './../../services/cart.service';
import { Product } from 'src/app/common/product';
import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  currentCategoryId: number = 1;
  searchMode:boolean=false;


  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      this.listProduct();
    });
  }

  listProduct() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');
    if(this.searchMode){
      this.handleSearchProducts();
    }
    else{
      this.handleListProducts();
    }
   
  }
  handleSearchProducts(){
    const theKeyword:string = this.route.snapshot.paramMap.get('keyword')!;
    this.productService.searchProduct(theKeyword).subscribe(
      data =>{
        this.products = data;
      }
    )
  }
handleListProducts(){
  const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
   
    
  if (hasCategoryId ) {
    this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
  } else {
    this.currentCategoryId = 1;
  }

  this.productService.getProductList(this.currentCategoryId).subscribe(
    data => {
      this.products = data;
    }
  );




}

addToCart(theProduct:Product){
  console.log(`Adding to cart:${theProduct.name}, ${theProduct.price}`);
  const  theCartItem =new CartItem(theProduct);
  this.cartService.addToCart(theCartItem);

}

}
