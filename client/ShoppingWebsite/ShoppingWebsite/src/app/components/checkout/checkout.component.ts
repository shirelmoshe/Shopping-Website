import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CheckoutService } from './../../services/checkout.service';
import { CartService } from './../../services/cart.service';

import { HttpClient } from '@angular/common/http';
import { Orders } from 'src/app/common/orders';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  checkoutFormGroup!: FormGroup;
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(
    private formBuilder: FormBuilder,
    private cartService: CartService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.checkoutFormGroup = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      street: ['', Validators.required],
      city: ['', Validators.required],
      country: ['', Validators.required],
      cardType: ['', Validators.required],
      nameOnCard: ['', Validators.required],
      cardNumber: ['', Validators.required],
      securityCode: ['', Validators.required],
      expirationMonth: ['', Validators.required],
      expirationYear: ['', Validators.required]
    });

    this.reviewCartDetails();
  }

  reviewCartDetails() {
    this.cartService.totalQuantity.subscribe(
      totalQuantity => (this.totalQuantity = totalQuantity)
    );

    this.cartService.totalPrice.subscribe(totalPrice => (this.totalPrice = totalPrice));
  }

  onSubmit() {
    if (this.checkoutFormGroup && this.checkoutFormGroup.valid) {
      const formValue = this.checkoutFormGroup.value;
      const order: Orders = {
        first_name: formValue.firstName,
        last_name: formValue.lastName,
        email: formValue.email,
        street: formValue.street,
        city: formValue.city,
        country: formValue.country,
        card_type: formValue.cardType,
        name_on_card: formValue.nameOnCard,
        card_number: formValue.cardNumber,
        security_code: formValue.securityCode,
        expiration_month: formValue.expirationMonth,
        expiration_year: formValue.expirationYear
      };

      this.http.post('http://localhost:8080/api/orders', order)
        .subscribe(
          response => {
            // Handle success response
            console.log('Order placed successfully:', response);
            // Optionally, navigate to a success page
            this.router.navigate(['/success']);
          },
          error => {
            // Handle error response
            console.error('Failed to place order:', error);
            // Optionally, display an error message to the user
          }
        );
    } else {
      console.log('Form data:', this.checkoutFormGroup.value);
      // Form is invalid or checkoutFormGroup is null, display an error message or perform some action
      console.error('Form is invalid or checkoutFormGroup is null.');
      // Optionally, display an error message to the user or perform some action
    }
  }

  getFormData(): Subscription {
    const url = 'http://localhost:8080/api/orders';
    const formData = this.checkoutFormGroup.value;
    console.log('Form data:', formData);
  
    return this.http.post(url, formData).subscribe(
      (response: any) => {
        console.log('Form data retrieved successfully', response);
        // Handle the retrieved form data
      },
      (error: any) => {
        console.error('Error occurred', error);
        // Handle any errors that occurred during the request
      }
    );
  }
  
}
