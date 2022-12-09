import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IActivity } from '../interfaces/activity.interface';

@Component({
  selector: 'app-add-activity',
  templateUrl: './add-activity.component.html',
  styleUrls: ['./add-activity.component.css']
})
export class AddActivityComponent {
  constructor(
    public dialogRef: MatDialogRef<AddActivityComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IActivity,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}