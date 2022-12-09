import { Component, Input, OnInit } from '@angular/core';
import { IPortActivitySchedule } from '../interfaces/port-activity-schedule';
import { IPortActivity } from '../interfaces/port-activity.interface';

@Component({
  selector: 'app-port-details-card',
  templateUrl: './port-details-card.component.html',
  styleUrls: ['./port-details-card.component.css']
})
export class PortDetailsCardComponent implements OnInit {

  @Input() public portActivity: IPortActivity;
  @Input() public portActivitySchedule : IPortActivitySchedule;
  
  constructor() { }

  ngOnInit(): void { }

}
