import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { SnackBarMessageService } from '../services/snack-bar-message.service';

@Component({
  selector: 'app-snack-bar',
  templateUrl: './snack-bar.component.html',
  styleUrls: ['./snack-bar.component.css'],
})
export class SnackBarComponent implements OnInit {

  public constructor(public dialogRef: MatDialogRef<SnackBarComponent>, private snackBarService: SnackBarMessageService) {}
  public message: string;
  ngOnInit(): void {
    this.snackBarService.message.subscribe(data => this.message = data)
  }
}
