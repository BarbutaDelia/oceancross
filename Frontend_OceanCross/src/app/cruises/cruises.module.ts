import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CruisesRoutingModule } from './cruises-routing.module';
import { AddCruiseComponent } from './add-cruise/add-cruise.component';
import { CardCruiseComponent } from './card-cruise/card-cruise.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { HomeComponent } from './home/home.component';
import { CruisePortCardComponent } from './cruise-port-card/cruise-port-card.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { CruiseComponent } from './cruise/cruise.component';
import { PortsModule } from '../ports/ports.module';
import { ActivityComponent } from './activity/activity.component';
import { AddActivityComponent } from './add-activity/add-activity.component';
import { FormCruiseActivityComponent } from './form-cruise-activity/form-cruise-activity.component';
import { HttpClientModule } from '@angular/common/http';
import { LineComponent } from './line/line.component';
import { AnimationComponent } from './animation/animation.component';
import { NgxPaginationModule } from 'ngx-pagination';
@NgModule({
  declarations: [
    AddCruiseComponent,
    CardCruiseComponent,
    HomeComponent,
    CardCruiseComponent,
    CruisePortCardComponent,
    WishlistComponent,
    AddActivityComponent,
    FormCruiseActivityComponent,
    ActivityComponent,
    CruiseComponent,
    LineComponent,
    AnimationComponent,
    
  ],
  imports: [
    CommonModule,
    CruisesRoutingModule,
    SharedModule,
    PortsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  exports: [HomeComponent]
})
export class CruisesModule { }
