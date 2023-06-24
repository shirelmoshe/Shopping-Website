
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})




export class ProductFormComponent implements OnInit {
  productForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService
  ) {}

  ngOnInit() {
    this.buildForm();
  }

  buildForm() {
    this.productForm = this.formBuilder.group({
      sku: ['', Validators.required],
      imageUrl: ['', Validators.required],
      active: [true],
      name: ['', Validators.required],
      description: [''],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      categoryId: ['', Validators.required]
    });
  }

  addProduct() {
    if (this.productForm.valid) {
      const productData = this.productForm.value;
      this.productService.addProduct(productData).subscribe(
        (response:any) => {
          // Handle success
          console.log('Product added successfully');
        },
        (error:any) => {
          // Handle error
          console.log('Error adding product', error);
        }
      );
    }
  }
}
