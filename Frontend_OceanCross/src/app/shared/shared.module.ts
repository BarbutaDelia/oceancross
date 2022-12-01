import { NgModule } from '@angular/core';
import {MatButtonModule} from '@angular/material/button'
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import {MatToolbarModule} from '@angular/material/toolbar';
@NgModule({
  declarations: [],
  imports: [MatButtonModule, MatTableModule, MatSortModule, MatToolbarModule],
  exports:[MatButtonModule, MatTableModule, MatSortModule, MatToolbarModule]
})
export class SharedModule { }
