import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.buildForm();
  }

  buildForm() {
    this.registrationForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      user_type: ['', Validators.required]
    });
  }

  registerUser() {
    if (this.registrationForm.valid) {
      const user = this.registrationForm.value;
      this.userService.registerUser(user).subscribe(
        () => {
          alert('Registration successful!');
          // Redirect to login page or perform any other necessary action
        },
        () => {
          alert('Registration failed. Please try again.');
        }
      );
    }
  }
}
