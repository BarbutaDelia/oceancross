import { CdkDragDrop, copyArrayItem, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ICruisePorts } from 'src/app/cruises/models/cruisePorts.interface';
import { IPortActivity } from 'src/app/cruises/models/portActivities.interface';
import { CruisesService } from 'src/app/cruises/services/cruises-service.service';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { IActivityRequest } from '../interfaces/activity-request.interface';
import { UserPortActivitiesService } from '../services/user-port-activities.service';

@Component({
  selector: 'app-port-activites',
  templateUrl: './port-activites.component.html',
  styleUrls: ['./port-activites.component.css']
})
export class PortActivitesComponent implements OnInit {

  public activities: IPortActivity[] = [];
  public myactivities: IPortActivity[] = [];
  public portID: number = 0;
  public cruiseID:number = 0;
  public cruiseport:ICruisePorts
  constructor(
    private userPortActivitiesService: UserPortActivitiesService, 
    private cruiseService: CruisesService,
    private router:ActivatedRoute,
    private snackBarMessageService: SnackBarMessageService
    ) { 
      const param = JSON.parse(this.router.snapshot.paramMap.get('data'));
      this.portID = param[1];
      this.cruiseID = param[0];
    }

  ngOnInit(): void {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    if(this.portID !== 0 && this.cruiseID !== 0) {
      this.cruiseService.getPortCruise(this.cruiseID, this.portID).subscribe(
        (cruiseport) => {
          this.cruiseport = cruiseport;
        })
        this.userPortActivitiesService
        .getPortAndUserActivities(tokenParse.id,this.portID,this.cruiseID) .subscribe((data)=> {
          this.activities = data.portActivities;
          this.myactivities = data.userActivities;
        });
    }
  }  
 
  drop(event: CdkDragDrop<IPortActivity[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    }
    const newActivity = event.previousContainer.data[event.previousIndex]
    const isActivity = this.myactivities.find((activity) => activity.id === newActivity.id)
    if(!isActivity) {
      copyArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }    
  }
  
  
  public dropWA(event: CdkDragDrop<IPortActivity[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    }
    else{
      const newActivity = event.previousContainer.data[event.previousIndex]
      const index = this.myactivities.findIndex((activity) => activity.id === newActivity.id)
      this.myactivities.splice(index, 1);
    }
    
  }

  public saveButton() {
    
    const request: IActivityRequest[] = []
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const userID = tokenParse.id;
    for (let i = 0; i < this.myactivities.length; i++) {
      const activityRequest :IActivityRequest = {
        userId: userID,
        cruiseId: this.cruiseID,
        portActivityScheduleId: this.myactivities[i].id
      }
      request.push(activityRequest)
    }
    this.userPortActivitiesService.deleteAllActivitiesFromUser(userID, this.cruiseID).subscribe(
      data => {
        this.userPortActivitiesService.addActivityToUser(request).subscribe(
          data => {
            this.snackBarMessageService.openSnackBar("Saved");
          }
        )
      }
    )
    
    
  }
}
