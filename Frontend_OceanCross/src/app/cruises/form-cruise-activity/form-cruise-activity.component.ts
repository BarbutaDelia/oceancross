import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PortsService } from 'src/app/ports/services/ports.services';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { AddActivityComponent } from '../add-activity/add-activity.component';
import { IActivity } from '../interfaces/activity.interface';
import { ICruise } from '../models/cruise.interface';
import { IOnboardActivities } from '../models/onboardActivities.interface';
import { CruisesService } from '../services/cruises-service.service';

@Component({
  selector: 'app-form-cruise-activity',
  templateUrl: './form-cruise-activity.component.html',
  styleUrls: ['./form-cruise-activity.component.css']
})
export class FormCruiseActivityComponent implements OnInit {

  @Input() public cruise: ICruise;
  @Input() public isComplet: boolean;
  @ViewChild('TableActivities') TableActivities = new MatSort();
  public displayedColumnsActivities: string[] = ['name', 'start_date','start_time','duration', 'location', 'delete'];
  public dataSourceActivities = new MatTableDataSource([]);
  

  constructor(public dialog: MatDialog, 
    private portService: PortsService,
    private snackBarMessageService: SnackBarMessageService,
    private cruisesService: CruisesService
    ) { }
  
  public ngOnInit(): void {
    this.portService.getPorts().subscribe( () => {
      this.dataSourceActivities.data = this.cruise.onboardActivities;
    })
  }

  public openDialog(): void {
    const newOnbordActivity : IOnboardActivities  = {
      id: -1,
      name: '',
      start_date: '',
      start_time:undefined,
      duration: undefined,
      location: ''
    }

    const data = {
      page_type: true,
      start_date: this.cruise.start_date,
      end_date: this.cruise.end_date,
      onBoardActivity: newOnbordActivity
    }
    if(this.isComplet) {
      const dialogRef = this.dialog.open(AddActivityComponent, {
        data: data
      });
  
      dialogRef.afterClosed().subscribe(result => {
        if(result) {
          const condition = this.checkCondition(result);
          if(condition) {
            this.cruise.onboardActivities.push(result.onBoardActivity);
            this.dataSourceActivities.data = this.cruise.onboardActivities;
          }
        }
      });
    } else {
      this.snackBarMessageService.openSnackBar("The cruise form is blank")

    }
    
  }
  
  public deleteOnboardActivity(onboardActivities:IOnboardActivities)  {
    if(onboardActivities.id === -1) {
      const index = this.cruise.onboardActivities.indexOf(onboardActivities);
      this.cruise.onboardActivities.splice(index, 1);
      this.dataSourceActivities.data = this.cruise.onboardActivities;
    }
    else {
      this.cruisesService.deleteOnboardActivity(this.cruise.id, onboardActivities.id)
      .subscribe(
        (data) =>{
          const index = this.cruise.onboardActivities.indexOf(onboardActivities);
          this.cruise.onboardActivities.splice(index, 1);
          this.dataSourceActivities.data = this.cruise.onboardActivities;
          this.snackBarMessageService.openSnackBar("Successfully  deleted")
        },
        error => this.snackBarMessageService.openSnackBar(error.message)

        )
    }
    
  }

  ngAfterViewInit() {    
    this.dataSourceActivities.sort = this.TableActivities;
  }

  public checkCondition(result) : boolean{
    if(result.onBoardActivity.name ==='' || 
      result.onBoardActivity.start_date === '' || 
      result.onBoardActivity.start_time === undefined ||
      result.onBoardActivity.location === '' ||
      result.onBoardActivity.duration === undefined
      ){
        this.snackBarMessageService.openSnackBar("Error:Blank Fields")
        return false;
    }
    this.snackBarMessageService.openSnackBar("Change successful")
    return true;
    
  }
  public updateOnboardActivity(onBoardActivity: IOnboardActivities){
    const modifiedOnBoardActivity: IOnboardActivities = {
      id: onBoardActivity.id,
      name: onBoardActivity.name,
      start_date: onBoardActivity.start_date,
      start_time: onBoardActivity.start_time,
      duration: onBoardActivity.duration,
      location:onBoardActivity.location
    }
    
    const data = {
      page_type: false,
      start_date: this.cruise.start_date,
      end_date: this.cruise.end_date,
      onBoardActivity: modifiedOnBoardActivity
    }

    if(this.isComplet){
      const dialogRef = this.dialog.open(AddActivityComponent, {
        data: data,
      });
  
      dialogRef.afterClosed().subscribe(result => {
        const condition = this.checkCondition(result);
        if(condition) {
          onBoardActivity.name = result.onBoardActivity.name
          onBoardActivity.start_date = result.onBoardActivity.start_date
          onBoardActivity.start_time = result.onBoardActivity.start_time
          onBoardActivity.duration = result.onBoardActivity.duration
          onBoardActivity.location = result.onBoardActivity.location
        }
      });
    } else {
      this.snackBarMessageService.openSnackBar("The cruise form is blank")

    }
    
   
  }
  
}
