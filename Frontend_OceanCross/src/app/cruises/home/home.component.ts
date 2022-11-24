import { Component, NgModule, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CardCruiseComponent } from '../card-cruise/card-cruise.component';
import { CruisesRoutingModule } from '../cruises-routing.module';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})


export class HomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    
    //aici defapt ar trebui sa facem get pentru cards
    //si pentru token de
    this.cards[0].id="0";
    this.cards[0].endDate="01";
    this.cards[0].startDate="02";
    this.cards[0].title="Ciupacabra";
    this.cards[3].id="3";
    this.cards[3].endDate="01";
    this.cards[3].startDate="02";
    this.cards[3].title="O.M.G";
    this.cards[2].id="2";
    this.cards[2].endDate="01";
    this.cards[2].startDate="02";
    this.cards[2].title="Orlando e pe zona";
    this.cards[1].id="1";
    this.cards[1].endDate="01";
    this.cards[1].startDate="02";
    this.cards[1].title="Dracula nu suge..sange";
    
  }
  ClickLogin()
  {
    this.router.navigateByUrl('/login');
  }
  ClickSingUp()
  {
    this.router.navigateByUrl('/sign-up');
  }
 
  
  cards:Card[]=[new Card(),new Card,new Card,new Card];

  
}
class Card
{
  constructor(){}
  id:String="";
  title="";
  endDate="";
  startDate="";
}


