import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PortsRoutingModule } from './ports-routing.module';
import { PortActivitesComponent } from './port-activites/port-activites.component';
import { PortDetailsCardComponent } from './port-details-card/port-details-card.component';
import { SharedModule } from '../shared/shared.module';
import { AddPortComponent } from './add-port/add-port.component';
import { PortsComponent } from './ports/ports.component';
import { PortsDropdownComponent } from './ports-dropdown/ports-dropdown.component';


@NgModule({
  declarations: [
    PortActivitesComponent,
    PortDetailsCardComponent,
    AddPortComponent,
    PortsComponent,
    PortsDropdownComponent
  ],
  imports: [
    CommonModule,
    PortsRoutingModule,
    SharedModule
  ],
  exports :[PortsComponent, PortsDropdownComponent]
})
export class PortsModule { }
