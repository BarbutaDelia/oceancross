import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { ICruise } from '../models/cruise.interface';
@Component({
  selector: 'app-card-cruise',
  templateUrl: './card-cruise.component.html',
  styleUrls: ['./card-cruise.component.css']
})

export class CardCruiseComponent implements OnInit 
{
  @Input() cruise:ICruise;
  @Input() enableButton: boolean;
  constructor(private router:Router, public localStorageService:LocalStorageService) {}
  ngOnInit(): void  {}  
 
  redirectToView(): void { 
    this.router.navigateByUrl("/cruise-details/"+ this.cruise.id);
  }
  public deleteCruise(){
    console.log(this.cruise.id)
  }
  public updateCruise(){
    console.log(this.cruise)
  }
  
}