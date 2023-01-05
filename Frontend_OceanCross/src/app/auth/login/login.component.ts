
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup  } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { IUser } from '../models/user.interface';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  public loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder, 
    private authService: AuthService,
    private snackBarService:SnackBarMessageService,
    public localStorageService:LocalStorageService,
    private router: Router)
    { }

  public ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  public onSubmit(): void {

    const values = this.loginForm.value;
    const username = values.username;
    const password = values.password;

    if((username === null || username === '') || (password === null || password === '')) {
      this.openSnackBar("Incomplete Login form")
    }
    else{
      const user: IUser = { username: username, password: password }
      this.authService.signIn(user).subscribe(
        data => {
          localStorage.setItem("data", JSON.stringify(data))
          this.localStorageService.changeData(data)
          this.router.navigateByUrl('/');
        },
        error => this.openSnackBar("Wrong credentials")
      )
    }
    this.loginForm.reset();
  }

  public openSnackBar(message: string) {
    this.snackBarService.openSnackBar(message)
  } 
 
}