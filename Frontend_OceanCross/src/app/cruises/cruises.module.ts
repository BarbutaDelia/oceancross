import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CruisesRoutingModule } from './cruises-routing.module';
import { AddCruiseComponent } from './add-cruise/add-cruise.component';
import { CardCruiseComponent } from './card-cruise/card-cruise.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { HomeComponent } from './home/home.component';
import { CruisePortCardComponent } from './cruise-port-card/cruise-port-card.component';
import { WishlistComponent } from './wishlist/wishlist.component';
@NgModule({
  declarations: [
    AddCruiseComponent,
    CardCruiseComponent,
    HomeComponent,
    CardCruiseComponent,
    CruisePortCardComponent,
    WishlistComponent
    
  ],
  imports: [
    CommonModule,
    CruisesRoutingModule,
    SharedModule
  ]
})
export class CruisesModule { }
