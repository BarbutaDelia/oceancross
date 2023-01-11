import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { ICruise } from '../models/cruise.interface';
import { ICruiseRequest } from '../models/cruiseRequest.interface';
import { CruisesService } from '../services/cruises-service.service';

@Component({
  selector: 'app-add-cruise',
  templateUrl: './add-cruise.component.html',
  styleUrls: ['./add-cruise.component.css']
})
export class AddCruiseComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder, 
    private activatedRoute: ActivatedRoute, 
    private cruisesService:CruisesService,
    private snackBarMessageService: SnackBarMessageService,
    private router: Router
    ) { }
  
  public startDateSprint: Date = new Date();
  public today: Date =  new Date();
  public addCruiseForm: FormGroup;
  public pageType:boolean

  public cruise : ICruise = {
    name: undefined,
    start_date: new Date(),
    end_date: new Date(),
    price: undefined,
    onboardActivities: [],
    cruisePorts: []
  };
  public cruiseID: number;
  
  ngOnInit(): void {
    const url = window.location.href
    this.pageType = url?.includes("add-cruise") ? true : false;
    if(!this.pageType) {
      this.cruiseID = Number(this.activatedRoute.snapshot.paramMap.get('id'));
      this.cruisesService.getCruise(this.cruiseID).subscribe( cruise => {
        this.cruise.name = cruise.name;
        this.cruise.start_date = new Date(cruise.start_date);
        this.cruise.end_date = new Date(cruise.end_date);
        this.cruise.price = cruise.price;
        this.cruise.onboardActivities = cruise.onboardActivities;
        this.cruise.cruisePorts = cruise.cruisePorts;
    })
    }

    this.addCruiseForm = this.formBuilder.group({
    name: new FormControl({ value: null, disabled: false }, this.changeName.bind(this)),
    price: new FormControl({ value: null, disabled: false }, this.changePrice.bind(this)),
    startDate: new FormControl( { value: null, disabled: false }, this.changeMinDate.bind(this)),
    endDate: new FormControl({ value: null, disabled: false }, this.changeMaxDate.bind(this))
  });
  
  }

  public changeMinDate(control: FormControl) {
    this.startDateSprint = new Date(control.value);
    this.cruise.start_date = this.startDateSprint
  }

  public changeMaxDate(control: FormControl) {
    this.cruise.end_date = new Date(control.value);
  }

  public changeName(control: FormControl) {
    this.cruise.name = control.value;
  }

  public changePrice(control: FormControl) {
    this.cruise.price = control.value;
  }

  public saveCruise()  {
    if(this.checkFirstForm) {
      const cruiseRequest :ICruiseRequest = {
        id: -1,
        name: this.cruise.name,
        start_date: this.formatDate(this.cruise.start_date),
        end_date: this.formatDate(this.cruise.end_date),
        price: this.cruise.price,
        onboardActivities: this.cruise.onboardActivities,
        cruisePorts: this.cruise.cruisePorts
      }
  
      this.cruisesService.addCruise(cruiseRequest).subscribe(
        (data) => {
          this.snackBarMessageService.openSnackBar("Successfully added");
          this.router.navigateByUrl('/');
        },
        (error) => this.snackBarMessageService.openSnackBar("Error: This cruise name is already in database")
      )
    }
    else {
      this.snackBarMessageService.openSnackBar("The cruise form is not filled out")
    }
  }

  public updateCruise(){
    if(this.checkFirstForm()) {
      const cruiseRequest :ICruiseRequest = {
        id: this.cruiseID,
        name: this.cruise.name,
        start_date: this.formatDate(this.cruise.start_date),
        end_date: this.formatDate(this.cruise.end_date),
        price: this.cruise.price,
        onboardActivities: this.cruise.onboardActivities,
        cruisePorts: this.cruise.cruisePorts
      }
  
      this.cruisesService.updateCruise(this.cruiseID, cruiseRequest).subscribe(
        (data) => {
          this.snackBarMessageService.openSnackBar("Successfully modified");
          this.router.navigateByUrl('/');
        },
        (error) => this.snackBarMessageService.openSnackBar(error.message)
      )
    }
    else {
      this.snackBarMessageService.openSnackBar("The cruise form is not filled out")
    }
    

  }

  public formatDate(date: Date) {
    const year = date.getFullYear();
    const month = this.padTo2Digits(date.getMonth() + 1);
    const day = this.padTo2Digits(date.getDate());
    return `${year}-${month}-${day}`;
  }

  private padTo2Digits(number: number) {
    return number.toString().padStart(2, '0');
  }
  public checkFirstForm() {
    if ( this.cruise.name === null || this.cruise.name === undefined ||  this.cruise.price === null )  return false;
    return true;
  }
  
}