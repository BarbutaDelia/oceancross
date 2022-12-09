import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { IActivity } from '../interfaces/activity.interface';
import { IPortActivitySchedule } from '../interfaces/port-activity-schedule';
import { IPortActivity } from '../interfaces/port-activity.interface';
import { IPort } from '../interfaces/port.interface';

@Component({
  selector: 'app-port-activites',
  templateUrl: './port-activites.component.html',
  styleUrls: ['./port-activites.component.css']
})
export class PortActivitesComponent implements OnInit {

  constructor() { }
  public port: IPort = {
    portName: "Port Name",
    arrivalDate: new Date(),
    arrivalTime: {
      hours: 0,
      minutes: 0
    },
    duration: 0
  }
  public activities: IActivity[];
  public myactivities: IActivity[];
  ngOnInit(): void {
  
  const activity: IActivity = {
    portActivitySchedule: this.portActivitySchedule,
    portActivity: this.portActivity
  }
  this.activities = [activity,activity,activity,activity,activity];
  this.myactivities = [activity,activity];
  }
  drop(event: CdkDragDrop<IActivity[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }
  public saveButton()
  {
    
  }


  public portActivity : IPortActivity = {
    id: 1,
    userID: 1,
    portID: 1,
    name: 'Activity_Name',
    imagePath: 'assets\\image\\download.jfif'
  }
  public portActivitySchedule : IPortActivitySchedule = {
    ID: 0,
    portActivityID: 0,
    startDate: new Date(),
    startTime: {
      hours: 0,
      minutes: 0
    },
    duration: 0,
    location: '',
    price: 0
  }
}
