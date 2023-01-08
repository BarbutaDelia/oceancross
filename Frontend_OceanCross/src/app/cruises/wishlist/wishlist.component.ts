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

  public cruisesPage: number = 1;
  public itemsPerPage: number = 2;

  constructor(private cruisesService:CruisesService, public localStorageService:LocalStorageService ) { }
  
  ngOnInit(): void {
    this.cruisesService.getCruises().subscribe( cruises => {this.cruises = cruises; this.cruisesFilter = cruises })
    
  }
  public set filter(filterBy: string) {
    filterBy = filterBy?.toLocaleLowerCase()?.trim();

    if (filterBy) {
        this.cruisesFilter = this.cruises.filter((cruise) => {
            const cruiseName = cruise.name.toLocaleLowerCase();
            return cruiseName.includes(filterBy);
        });

    } else this.cruisesFilter = this.cruises;

  }
  public onPageChange(event: number): void {
    this.cruisesPage = event;
  }

}