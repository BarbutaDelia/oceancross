import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PortMapComponent } from './port/port.component';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatAutocompleteModule} from '@angular/material/autocomplete'
import { GoogleMapsModule } from '@angular/google-maps';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PortsMapComponent } from './ports-map/ports-map.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    PortMapComponent,
    PortsMapComponent
  ],
  imports: [
    CommonModule,
    MatAutocompleteModule,
    MatButtonModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    GoogleMapsModule
  ],
  exports: [PortMapComponent, PortsMapComponent]
})
export class MapModule { }
