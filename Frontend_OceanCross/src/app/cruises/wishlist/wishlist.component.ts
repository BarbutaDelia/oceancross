import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit, Output } from '@angular/core';
import { TitleStrategy } from '@angular/router';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { __importDefault } from 'tslib';
import { ICruise } from '../models/cruise.interface';
import { CruisesService } from '../services/cruises-service.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  public cruises : ICruise[] = [];
  public cruisesFilter: ICruise[] = [];
  constructor(private cruisesService:CruisesService, public localStorageService:LocalStorageService ) { }
  
  ngOnInit(): void 
  {
    this.cruisesService.getCruises().subscribe( cruises => {this.cruises = cruises; this.cruisesFilter = cruises })
    
  }

}