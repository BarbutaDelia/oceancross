import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCruiseComponent } from './add-cruise/add-cruise.component';
import { CardCruiseComponent } from './card-cruise/card-cruise.component';
import { CruisePortCardComponent } from './cruise-port-card/cruise-port-card.component';
import { CruiseComponent } from './cruise/cruise.component';
import { HomeComponent } from './home/home.component';
import { WishlistComponent } from './wishlist/wishlist.component';

const routes: Routes = [
  {path:"cruise/:id", component: AddCruiseComponent},
  {path:"add-cruise", component: AddCruiseComponent},
  {path:"card-cruise", component: CardCruiseComponent},
  {path:"cruise-details", component: CruiseComponent},
  {path:"cruise-details/:id", component: CruiseComponent},
  {path:"cruise-port-card", component: CruisePortCardComponent},
  {path:"wishlist", component: WishlistComponent},
  {path:"home", component: HomeComponent},
  {path:"", component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CruisesRoutingModule { }
