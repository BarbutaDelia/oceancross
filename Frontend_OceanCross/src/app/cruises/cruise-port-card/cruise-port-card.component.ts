import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Card, Port } from '../wishlist/wishlist.component';

@Component({
  selector: 'app-cruise-port-card',
  templateUrl: './cruise-port-card.component.html',
  styleUrls: ['./cruise-port-card.component.css']
})
export class CruisePortCardComponent implements OnInit {

  constructor(private router:Router) { }
  @Input() card:Card=new Card();
  ngOnInit(): void {
  }
  viewActivities(id:String):void
  {
    this.router.navigateByUrl('/port-activities/'+id);
  }

}
