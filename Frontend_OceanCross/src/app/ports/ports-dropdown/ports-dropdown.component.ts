import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AddPortComponent } from '../add-port/add-port.component';
import { IPort } from '../interfaces/port.interface';

@Component({
  selector: 'app-ports-dropdown',
  templateUrl: './ports-dropdown.component.html',
  styleUrls: ['./ports-dropdown.component.css']
})
export class PortsDropdownComponent implements OnInit {

  public selectedPorts: IPort[] = [];
  public newPort: IPort = {
    portName: undefined,
    arrivalDate: undefined,
    arrivalTime: undefined,
    duration: undefined
  };
  public ports: IPort[];
  animalControl = new FormControl<IPort | null>(null, this.addSelectedPort.bind(this));
  constructor(public dialog: MatDialog) { }
  
  ngOnInit(): void {
    this.ports = [
      {portName: 'test1', arrivalDate: new Date(), arrivalTime: {hours: 0,minutes: 0}, duration: 10},
      {portName: 'test2', arrivalDate: new Date(), arrivalTime: {hours: 0,minutes: 0}, duration: 10},
      {portName: 'test3', arrivalDate: new Date(), arrivalTime: {hours: 0,minutes: 0}, duration: 10},
      {portName: 'test4', arrivalDate: new Date(), arrivalTime: {hours: 0,minutes: 0}, duration: 10},
      {portName: 'test5', arrivalDate: new Date(), arrivalTime: {hours: 0,minutes: 0}, duration: 10},
      {portName: 'test6', arrivalDate: new Date(), arrivalTime: {hours: 0,minutes: 0}, duration: 10}
    ];
  }
  public addSelectedPort(control: FormControl)
  {
    if(control.value) {
      const newPort = this.selectedPorts.indexOf(control.value)
      if(newPort  === -1) 
      {
        console.log(newPort);
        console.log(control.value);
        this.selectedPorts.push(control.value);

      }
    }
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(AddPortComponent, {
      data: this.newPort
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result) {
        this.selectedPorts.push(result);
        this.newPort = result;
      }
    });
  }
  
}
