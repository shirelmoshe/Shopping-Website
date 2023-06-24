import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.buildForm();
  }

  buildForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  loginUser() {
    if (this.loginForm.valid) {
      const credentials = this.loginForm.value;
      this.userService.loginUser(credentials).subscribe(
        (response: any) => {
          const token = response.token;
          // Store the token in local storage or a cookie
          localStorage.setItem('token', token);
          // Redirect to the desired page or perform any other necessary action
        },
        (error: any) => {
          // Handle login error, such as displaying an error message
          console.log(error);
        }
      );
    }
  }
}
