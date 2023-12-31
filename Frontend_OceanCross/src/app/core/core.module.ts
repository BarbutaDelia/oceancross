import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { AdminMenuComponent } from './admin-menu/admin-menu.component';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { UserAuthMenuComponent } from './user-auth-menu/user-auth-menu.component';
import { SharedModule } from '../shared/shared.module';
import { AuthService } from '../auth/services/auth.service';
import { SnackBarMessageService } from '../shared/services/snack-bar-message.service';
import { CruisesService } from '../cruises/services/cruises-service.service';
import { LocalStorageService } from '../shared/services/local-storage.service';
import { PortsService } from '../ports/services/ports.services';
import { WishlistService } from '../cruises/services/wishlist-service.service';
import { UserPortActivitiesService } from '../ports/services/user-port-activities.service';

@NgModule({
  declarations: [ 
    AdminMenuComponent, UserMenuComponent, UserAuthMenuComponent
  ],
  imports: [
    CommonModule,
    CoreRoutingModule,
    SharedModule,
  ],
  exports: [AdminMenuComponent,UserMenuComponent,UserAuthMenuComponent ],
  providers:[ AuthService, CruisesService,SnackBarMessageService, LocalStorageService, PortsService, WishlistService,UserPortActivitiesService  ],
})
export class CoreModule { }
