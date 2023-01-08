import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ILoginInfo } from 'src/app/auth/models/sign-info.interface';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';

@Component({
  selector: 'app-user-auth-menu',
  templateUrl: './user-auth-menu.component.html',
  styleUrls: ['./user-auth-menu.component.css']
})
export class UserAuthMenuComponent implements OnInit {

  constructor(private router: Router, private localStorageService:LocalStorageService) { }

  ngOnInit(): void {
  }
  public logout() {
    const dataStorage:ILoginInfo = {
      token: '',
      type: '',
      id: 0,
      username: '',
      role: ''
    }
    localStorage.setItem("data", JSON.stringify(dataStorage))
    this.localStorageService.changeData(JSON.parse(localStorage.getItem("data")))
    this.router.navigateByUrl('/');
  }
}

