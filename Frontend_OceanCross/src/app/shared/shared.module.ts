import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSelectModule} from '@angular/material/select';
import {MatDialogModule} from '@angular/material/dialog';
@NgModule({
  declarations: [],
  imports: [MatButtonModule, MatTableModule, MatSortModule, MatToolbarModule,
    MatButtonModule, 
    DragDropModule, 
    MatDatepickerModule, 
    ReactiveFormsModule, 
    MatFormFieldModule, 
    MatNativeDateModule, 
    MatInputModule, 
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatExpansionModule,
    MatIconModule,
    MatMenuModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatDialogModule,
    FormsModule],
  exports:[MatButtonModule, MatTableModule, MatSortModule, MatToolbarModule,MatButtonModule, 
    DragDropModule, 
    MatDatepickerModule, 
    ReactiveFormsModule, 
    MatFormFieldModule, 
    MatNativeDateModule, 
    MatInputModule, 
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatExpansionModule,
    MatIconModule,
    MatMenuModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatDialogModule,
    FormsModule],
})
export class SharedModule { }
