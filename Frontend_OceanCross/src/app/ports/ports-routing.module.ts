import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PortActivitesComponent } from './port-activites/port-activites.component';

const routes: Routes = [
  {
    path:"port-activities/:id", component: PortActivitesComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PortsRoutingModule { }
