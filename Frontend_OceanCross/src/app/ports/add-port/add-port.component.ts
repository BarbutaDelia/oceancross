import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IPort } from '../interfaces/port.interface';

@Component({
  selector: 'app-add-port',
  templateUrl: './add-port.component.html',
  styleUrls: ['./add-port.component.css']
})
export class AddPortComponent {
  constructor(
    public dialogRef: MatDialogRef<AddPortComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IPort,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}