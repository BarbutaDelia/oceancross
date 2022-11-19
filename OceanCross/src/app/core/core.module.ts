import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { AdminMenuComponent } from './admin-menu/admin-menu.component';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { UserAuthMenuComponent } from './user-auth-menu/user-auth-menu.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [ 
    AdminMenuComponent, UserMenuComponent, UserAuthMenuComponent
  ],
  imports: [
    CommonModule,
    CoreRoutingModule,
    SharedModule
  ],
  exports: [AdminMenuComponent,UserMenuComponent,UserAuthMenuComponent ]
})
export class CoreModule { }
