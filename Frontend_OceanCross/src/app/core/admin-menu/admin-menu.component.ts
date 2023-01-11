import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ILoginInfo } from 'src/app/auth/models/sign-info.interface';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  constructor(private router: Router, private localStorageService:LocalStorageService) { }

  ngOnInit(): void {
  }
  public logout() {
    const dataStorage:ILoginInfo = {
      token: '',
      type: '',
      id: -1,
      username: '',
      role: ''
    }
    localStorage.setItem("data", JSON.stringify(dataStorage))
    this.localStorageService.changeData(JSON.parse(localStorage.getItem("data")))
    this.router.navigateByUrl('/');
  }
}
