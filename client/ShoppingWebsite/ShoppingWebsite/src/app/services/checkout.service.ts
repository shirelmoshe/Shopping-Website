import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Orders } from '../common/orders';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  private purchaseUrl = 'http://localhost:8080/api/orders';

  constructor(private httpClient: HttpClient) { }

  placeOrder(order: Orders): Observable<any> {
    return this.httpClient.post<Orders>(this.purchaseUrl, order);
  }
}
