import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PortsRoutingModule } from './ports-routing.module';
import { PortActivitesComponent } from './port-activites/port-activites.component';
import { PortDetailsCardComponent } from './port-details-card/port-details-card.component';


@NgModule({
  declarations: [
    PortActivitesComponent,
    PortDetailsCardComponent
  ],
  imports: [
    CommonModule,
    PortsRoutingModule
  ]
})
export class PortsModule { }
