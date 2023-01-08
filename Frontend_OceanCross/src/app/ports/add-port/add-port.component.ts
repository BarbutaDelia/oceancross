import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ICruisePorts } from 'src/app/cruises/models/cruisePorts.interface';
import { IPort } from '../interfaces/port.interface';
import { PortsService } from '../services/ports.services';
@Component({
  selector: 'app-add-port',
  templateUrl: './add-port.component.html',
  styleUrls: ['./add-port.component.css']
})
export class AddPortComponent implements OnInit {
  public portControl = new FormControl<IPort | null>(null, this.addSelectedPort.bind(this));
  public dateControl = new FormControl<Date | null>(null, this.addSelectedDate.bind(this));
  public ports:IPort[] = [];

  constructor(
    public dialogRef: MatDialogRef<AddPortComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {page_type: boolean, start_date: Date, end_date:Date, cruisePort: ICruisePorts},
    public portService: PortsService
  ) {}

  public ngOnInit(): void {
   this.portService.getPorts().subscribe(
    (ports) =>{
      this.ports = ports; 
      if(!this.data.page_type) {
        this.dateControl.setValue(new Date(this.data.cruisePort.arrival_date))
        this.portControl.setValue(this.ports.find((port) => port.name === this.data.cruisePort.name))
      }
    })
    
  }

  public onNoClick(): void {
    this.dialogRef.close();
  }

  public addSelectedPort(control: FormControl)
  {
    if(control.value) {
     this.data.cruisePort.name = control.value.name;
     this.data.cruisePort.port_id = control.value.id;
    }
  }

  public addSelectedDate(control: FormControl){
    if(control.value) {
      this.data.cruisePort.arrival_date = this.formatDate(new Date(control.value))
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