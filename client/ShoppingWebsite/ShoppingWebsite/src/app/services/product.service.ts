import { ProductCategory } from './../common/product-category';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
 

 
  private baseUrl = 'http://localhost:8080/api/products';
  private categoryUrl = 'http://localhost:8080/api/product-category';
  private addProductApi = 'http://localhost:8080/api/add';

  constructor(private httpClient: HttpClient) { }

  getProductList(theCategoryId: number): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`;
  
    return this.httpClient.get<GetResponseProduct>(searchUrl).pipe(
      map(response => response._embedded.products)
    );
  }
  addProduct(productData: Product): Observable<Product> {
    return this.httpClient.post<Product>(`${this.addProductApi}/add`, productData);
  }
  
  getProductCategories():Observable<ProductCategory[]>{
    return this.httpClient.get<GetResponseProductCatrgory>(this.categoryUrl).pipe(
      map(response => response._embedded.productCategory)
    );
  }

  getProductId(theProductId: number):Observable<Product> {
    const productUrl =`${this.baseUrl}/${theProductId}`;

    return this.httpClient.get<Product>(productUrl);
     }


  searchProduct(theKeyword: string):Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${theKeyword}`;
  
    return this.httpClient.get<GetResponseProduct>(searchUrl).pipe(
      map(response => response._embedded.products)
    );
  
  }



 
  


  
}
interface GetResponseProduct {
  _embedded: {
    products: Product[];
  }
}

interface GetResponseProductCatrgory {
  _embedded: {
    productCategory: ProductCategory[];
  }
}



