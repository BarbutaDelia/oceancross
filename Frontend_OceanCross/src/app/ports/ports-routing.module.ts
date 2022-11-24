import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PortActivitesComponent } from './port-activites/port-activites.component';
import { PortDetailsCardComponent } from './port-details-card/port-details-card.component';

const routes: Routes = [
  {
    path:"port-activities/:id", component: PortActivitesComponent
  },
  {
    path:"port-card", component: PortDetailsCardComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PortsRoutingModule { }
