import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SharedModule } from '../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { AnimationComponent } from './animation/animation.component';

@NgModule({
  declarations: [
    LoginComponent,
    SignUpComponent,
    AnimationComponent
  ],
  imports: [
    SharedModule,
    CommonModule,
    AuthRoutingModule,
    ReactiveFormsModule
  ]

})
export class AuthModule { }
