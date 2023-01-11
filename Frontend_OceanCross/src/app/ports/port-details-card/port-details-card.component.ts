import { Component, Input, OnInit } from '@angular/core';
import { IPortActivity } from 'src/app/cruises/models/portActivities.interface';

@Component({
  selector: 'app-port-details-card',
  templateUrl: './port-details-card.component.html',
  styleUrls: ['./port-details-card.component.css']
})
export class PortDetailsCardComponent implements OnInit {

  @Input() public portActivity: IPortActivity;
  
  constructor() { }

  ngOnInit(): void { }
  
   public addImage(activity:IPortActivity):string {
      return "http://localhost:8080/" + activity.image
   } 

}
