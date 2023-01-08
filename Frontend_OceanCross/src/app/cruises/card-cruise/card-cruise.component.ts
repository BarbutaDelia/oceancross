import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { ICruise } from '../models/cruise.interface';
import { CruisesService } from '../services/cruises-service.service';
@Component({
  selector: 'app-card-cruise',
  templateUrl: './card-cruise.component.html',
  styleUrls: ['./card-cruise.component.css']
})

export class CardCruiseComponent implements OnInit 
{
  @Input() cruise:ICruise;
  @Input() enableButton: boolean;
  constructor(private router:Router, 
    public localStorageService:LocalStorageService, 
    public cruisesService: CruisesService,
    public snackBarMessageService :SnackBarMessageService
    ) {}
  ngOnInit(): void  {}  
 
  redirectToView(): void { 
    this.router.navigateByUrl("/cruise-details/"+ this.cruise.id);
  }
  public deleteCruise(cruise) {
    this.cruisesService.deleteCruise(cruise.id).subscribe(
      data => {
        this.snackBarMessageService.openSnackBar("Successfully deleted")
        location.reload();
      },
      error => this.snackBarMessageService.openSnackBar("Not found"),
    
      
      
    )
  }
  public updateCruise() {
    this.router.navigateByUrl("/cruise/" + this.cruise.id);
  }
  
}