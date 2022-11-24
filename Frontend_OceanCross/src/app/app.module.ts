import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { CruisesModule } from './cruises/cruises.module';
import { PortsModule } from './ports/ports.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from './core/core.module';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    CoreModule,
    AppRoutingModule,
    AuthModule,
    CruisesModule,
    PortsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
