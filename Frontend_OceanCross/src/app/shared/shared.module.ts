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
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBarModule, MAT_SNACK_BAR_DEFAULT_OPTIONS} from '@angular/material/snack-bar';
import { SnackBarComponent } from './snack-bar/snack-bar.component';

const modules = [MatButtonModule, MatTableModule, MatSortModule, MatToolbarModule, 
                  DragDropModule, 
                  MatDatepickerModule, 
                  ReactiveFormsModule, 
                  MatFormFieldModule, 
                  MatNativeDateModule, 
                  MatInputModule, 
                  BrowserAnimationsModule,
                  MatExpansionModule,
                  MatIconModule,
                  MatMenuModule,
                  MatSlideToggleModule,
                  MatSelectModule,
                  MatDialogModule,
                  FormsModule];
@NgModule({
  declarations: [
    SnackBarComponent
  ],
  imports: modules,
  exports:[modules,SnackBarComponent],
  providers:[ 
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: { duration: 1500 }},     
    {provide: MatDialogRef,useValue: {}}
  ]
})
export class SharedModule { }
