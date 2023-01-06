import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { IUser } from '../models/user.interface';

@Injectable({providedIn: "root"})
export class AuthService {

  constructor(private http: HttpClient) { }

  public signIn(user:IUser): Observable<any> {
    return this.http.post('/api/auth/signin', user )
  }

  public signUp(user:IUser): Observable<any> {
    const body = user;
    return this.http.post('/api/auth/signup', body );
  }

}
