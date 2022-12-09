import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AddActivityComponent } from '../add-activity/add-activity.component';
import { IActivity } from '../interfaces/activity.interface';

@Component({
  selector: 'app-form-cruise-activity',
  templateUrl: './form-cruise-activity.component.html',
  styleUrls: ['./form-cruise-activity.component.css']
})
export class FormCruiseActivityComponent implements OnInit {

  public selectedActivities: IActivity[] = [];
  public newActivity: IActivity = {
    activityName: undefined,
    startDate: undefined,
    startTime: undefined,
    duration: undefined,
    location: undefined
  };
  public activities: IActivity[];
  activityControl = new FormControl<IActivity | null>(null, this.addSelectedPort.bind(this));
  constructor(public dialog: MatDialog) { }
  
  ngOnInit(): void {
    this.activities = [
      {activityName: 'test1', startDate: new Date(), startTime : {hours: 0,minutes: 0}, duration: 10, location: "Unde vreau eu"},
      {activityName: 'test2', startDate: new Date(), startTime: {hours: 0,minutes: 0}, duration: 10, location: "Unde vreau eu"},
      {activityName: 'test3', startDate: new Date(), startTime: {hours: 0,minutes: 0}, duration: 10, location: "Unde vreau eu"},
      {activityName: 'test4', startDate: new Date(), startTime: {hours: 0,minutes: 0}, duration: 10, location: "Unde vreau eu"},
      {activityName: 'test5', startDate: new Date(), startTime: {hours: 0,minutes: 0}, duration: 10, location: "Unde vreau eu"},
      {activityName: 'test6', startDate: new Date(), startTime: {hours: 0,minutes: 0}, duration: 10, location: "Unde vreau eu"},
    ];
  }
  public addSelectedPort(control: FormControl)
  {
    if(control.value) {
      const newPort = this.selectedActivities.indexOf(control.value)
      if(newPort  === -1) 
      {
        console.log(newPort);
        console.log(control.value);
        this.selectedActivities.push(control.value);

      }
    }
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(AddActivityComponent, {
      data: this.newActivity
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result) {
        this.selectedActivities.push(result);
        this.newActivity = result;
      }
    });
  }
  
}
