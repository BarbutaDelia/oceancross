import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { ISignUp } from '../models/sign-up.interface';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  signupForm: FormGroup;
  typesControl = new FormControl<string | null>(null);
  types = ["vendor", "user"]
  constructor( 
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private snackBarService:SnackBarMessageService,
    private router: Router){}

  ngOnInit(): void {
      this.signupForm = this.formBuilder.group({
        name: ['', Validators.required],
        username: ['', Validators.required],
        password: ['', Validators.required],
        confirmpassword: ['', Validators.required]
        
      });
  }
  onSubmit(): void {
    const values = this.signupForm.value;
    const username = values.username;
    const password = values.password;

    if((username === null || username === '') || (password === null || password === '')) {
      this.openSnackBar("Incomplete Login form")
    }
    else{
      const signUp = this.checkForm();
      if(signUp !== null){
        this.authService.signUp(signUp).subscribe(
          data => {
            this.router.navigateByUrl('/login');
          },
          error => this.openSnackBar(error.message)
        )
        }
    }
    
  }
  public openSnackBar(message: string) {
    this.snackBarService.openSnackBar(message)
  }

  public checkForm(): ISignUp{
    const values = this.signupForm.value;
    const name = values.name;
    const username = values.username;
    const password = values.password;
    const confimPassword = values.confirmpassword;
    const userType = this.typesControl.value

    if((name === null || name === '') || (username === null || username === '') || (password === null || password === '')) {
      this.openSnackBar("Incomplete Sign Up form")
      return null;
    }

    if(password !== confimPassword){
      this.openSnackBar("Incorrect password")
      return null;
    }
    
    if(userType === null) this.openSnackBar("Select an account type")
    const signUp: ISignUp = {
      username: username,
      password: password,
      name: name,
      role_name: userType
    }
    return signUp;
  }

}