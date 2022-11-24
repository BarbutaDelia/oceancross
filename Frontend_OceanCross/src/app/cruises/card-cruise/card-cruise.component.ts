import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-card-cruise',
  templateUrl: './card-cruise.component.html',
  styleUrls: ['./card-cruise.component.css']
})
export class CardCruiseComponent implements OnInit 
{
  
  constructor(private router:Router)
  {
  }
  
  ngOnInit(): void 
  {
    
  }
  @Input() title:String="";
  @Input() endDate:String="";
  @Input() startDate:String="";
  @Input() id:String="";
  @Input() enableView:String="0";
  redirectToView():void
  { 
    this.router.navigateByUrl("/cruise-details/"+this.id);
  }
  

}
