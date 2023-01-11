import { Component, OnInit } from '@angular/core';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { ICruise } from '../models/cruise.interface';
import { WishlistService } from '../services/wishlist-service.service';

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

  constructor(private wishlistService:WishlistService, public localStorageService:LocalStorageService ) { }
  
  ngOnInit(): void {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    if(tokenParse.id !== -1)  {
       this.wishlistService.getCruises(tokenParse.id)
       .subscribe( cruises => {this.cruises = cruises; this.cruisesFilter = cruises })
    }
    
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