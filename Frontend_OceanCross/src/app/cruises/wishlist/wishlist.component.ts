import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit, Output } from '@angular/core';
import { TitleStrategy } from '@angular/router';
import { __importDefault } from 'tslib';


@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  constructor() { }
  
  ngOnInit(): void 
  {
    this.ports.push(new Port("Cucunamaci","7"));
    this.ports.push(new Port("Hawai","6"));
    
    this.cards.push(new Card("Ciuwawa","10.10.2022","10.12.2022",this.ports,"0"));

    this.cards.push(new Card("Big Foot","10.10.2022","10.12.2022",this.ports,"1"));
    
  }
  
  ports:Array<Port>=[];
  cards:Array<Card>=[];

}

export class Port
{
  constructor(name:String,id:String)
  {
    this.id=id;
    this.name=name;
  }
  name:String=""
  id:String=""
}
export class Card
{
  ports:Array<Port>=[];
  title:String="";
  startDate:String="";
  endDate:String=""
  id:String=""
  constructor(title:String="",sD:String="",eD:String="",p:Array<Port>=[],i:String="")
  {
    this.endDate=eD;
    this.startDate=sD;
    this.title=title;
    this.ports=p;
    this.id=i;
  }
}
