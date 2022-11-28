import { NgModule } from '@angular/core';
import {MatButtonModule} from '@angular/material/button'
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
@NgModule({
  declarations: [],
  imports: [MatButtonModule, MatTableModule, MatSortModule],
  exports:[MatButtonModule, MatTableModule, MatSortModule]
})
export class SharedModule { }
