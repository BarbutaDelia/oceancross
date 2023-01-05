import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BehaviorSubject } from 'rxjs';
import { SnackBarComponent } from '../snack-bar/snack-bar.component';

@Injectable({providedIn: "root"})
export class SnackBarMessageService {
  constructor( public snackBar: MatSnackBar) { }
  public message: BehaviorSubject<string> = new BehaviorSubject("");
  
  public openSnackBar(message: string) {
    this.message.next(message);
    this.snackBar.openFromComponent(SnackBarComponent)
  }
}
