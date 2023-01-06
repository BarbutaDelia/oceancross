import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ILoginInfo } from 'src/app/auth/models/sign-info.interface';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { ICruise } from '../models/cruise.interface';
import { CruisesService } from '../services/cruises-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})

export class HomeComponent implements OnInit {

  public cruises : ICruise[] = [];
  public cruisesFilter: ICruise[] = [];

  public cruisesPage: number = 1;
  public itemsPerPage: number = 4;

  public connectedUser : ILoginInfo

  constructor(private router: Router, private cruisesService:CruisesService, public localStorageService:LocalStorageService ) { }

  ngOnInit(): void {
    this.cruisesService.getCruises().subscribe( cruises => {this.cruises = cruises; this.cruisesFilter = cruises })
  }

  public clickLogin(): void {
    this.router.navigateByUrl('/login');
  }

  public clickSingUp():void  {
    this.router.navigateByUrl('/sign-up');
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
  public addCruise(){
    this.router.navigateByUrl('/add-cruise');
  }
}