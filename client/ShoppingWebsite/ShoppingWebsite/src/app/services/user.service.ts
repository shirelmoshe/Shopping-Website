import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../common/user';
@Injectable()
export class UserService {
  private apiUrl = 'http://localhost:8080/api/register';
  private loginUrl = 'http://localhost:8080/api/login';
  constructor(private http: HttpClient) {}

  registerUser(user: User): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }
  loginUser(user: User): Observable<any> {
    return this.http.post<any>(this.loginUrl, user);
  }
}