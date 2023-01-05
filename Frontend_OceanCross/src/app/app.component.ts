import { Component, OnInit } from '@angular/core';
import { ILoginInfo } from './auth/models/sign-info.interface';
import { LocalStorageService } from './shared/services/local-storage.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

 public connectedUser : ILoginInfo

  constructor(public localStorageService:LocalStorageService) { }
  ngOnInit(): void {
  }

}