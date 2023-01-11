import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ICruise } from '../models/cruise.interface';

@Component({
  selector: 'app-cruise-port-card',
  templateUrl: './cruise-port-card.component.html',
  styleUrls: ['./cruise-port-card.component.css']
})
export class CruisePortCardComponent implements OnInit {

  constructor(private router:Router) { }
  @Input() cruise:ICruise;
  ngOnInit(): void { }
  private async viewActivities(id:String) {
    await this.router.navigate(['/port-activities/'+id, {data: JSON.stringify([this.cruise.id, id])}]);
  }

}
