import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';

@Component({
  selector: 'app-product-deatails',
  templateUrl: './product-deatails.component.html',
  styleUrls: ['./product-deatails.component.css']
})
export class ProductDeatailsComponent implements OnInit{

  product!:Product;
  constructor(private productService:ProductService,private route:ActivatedRoute){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.handleProductDeairs();
    })
      
  }
  handleProductDeairs(){
    const theProductId :number = + this.route.snapshot.paramMap.get('id')!;
    this.productService.getProductId(theProductId).subscribe(
      data => {
        this.product = data;
      }
    )
  }

}
