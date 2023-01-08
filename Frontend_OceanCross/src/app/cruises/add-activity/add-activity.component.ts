import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PortsService } from 'src/app/ports/services/ports.services';
import { IPort } from '../interfaces/port.interface';
import { IOnboardActivities } from '../models/onboardActivities.interface';

@Component({
  selector: 'app-add-activity',
  templateUrl: './add-activity.component.html',
  styleUrls: ['./add-activity.component.css']
})
export class AddActivityComponent implements OnInit {
  public dateControl = new FormControl<Date | null>(null, this.addSelectedDate.bind(this));
  public ports:IPort[] = [];
  constructor(
    public dialogRef: MatDialogRef<AddActivityComponent>,
    public portService: PortsService,
    @Inject(MAT_DIALOG_DATA) public info: {page_type: boolean, start_date: Date, end_date:Date, onBoardActivity: IOnboardActivities},
  ) {}

  public ngOnInit(): void {
    if(!this.info.page_type) {
      this.dateControl.setValue(new Date(this.info.onBoardActivity.start_date))
     
    }
  }

  public onNoClick(): void {
    this.dialogRef.close();
  }
  public addSelectedDate(control: FormControl)
  {
    if(control.value) {
      this.info.onBoardActivity.start_date = this.formatDate(new Date(control.value))
    }
  }
  public formatDate(date: Date) {
    const year = date.getFullYear();
    const month = this.padTo2Digits(date.getMonth() + 1);
    const day = this.padTo2Digits(date.getDate());
    return `${year}-${month}-${day}`;
  }

  private padTo2Digits(number: number) {
    return number.toString().padStart(2, '0');
  }
}