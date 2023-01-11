import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ILoginInfo } from 'src/app/auth/models/sign-info.interface';

@Injectable({providedIn: "root"})
export class LocalStorageService {
  public data: BehaviorSubject<ILoginInfo> = new BehaviorSubject({token:'', type:'', id: -1, username:'', role:''});
  public connectedUser : ILoginInfo;
  constructor( ) {
    const login = JSON.parse(localStorage.getItem("data"))
    this.connectedUser = {token:'', type:'', id: -1, username:'', role:''}
    if(login?.username)
    {
      this.data.next(login)
      this.connectedUser = login;
    }
   }
  
  public changeData(data: ILoginInfo) {
    this.data.next(data);
    this.connectedUser = data;
  }
}
