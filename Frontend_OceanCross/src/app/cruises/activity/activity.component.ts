import { Component, Input, OnInit } from '@angular/core';
import { IActivity } from '../interfaces/activity.interface';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  constructor() { }
  @Input() public activities: IActivity[];
  ngOnInit(): void {

  }
  public deleteElem(activity:IActivity)
  {
    const index = this.activities.indexOf(activity);
    this.activities.splice(index, 1);
  }

}
