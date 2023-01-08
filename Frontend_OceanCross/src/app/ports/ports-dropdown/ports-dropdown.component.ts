import { Component, OnInit, Input, ViewChild} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ICruise } from 'src/app/cruises/models/cruise.interface';
import { ICruisePorts } from 'src/app/cruises/models/cruisePorts.interface';
import { CruisesService } from 'src/app/cruises/services/cruises-service.service';
import { PortMapComponent } from 'src/app/map/port/port.component';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';
import { AddPortComponent } from '../add-port/add-port.component';
import { PortsService } from '../services/ports.services';

@Component({
  selector: 'app-ports-dropdown',
  templateUrl: './ports-dropdown.component.html',
  styleUrls: ['./ports-dropdown.component.css']
})
export class PortsDropdownComponent implements OnInit {
  
  @Input() public cruise: ICruise;
  @Input() public isComplet: boolean;
  @ViewChild("") public table: MatTable<any>

  public displayedColumnsPorts: string[] = ['name','arrival_date', 'arrival_time', 'duration','delete'];
  public dataSourcePorts = new MatTableDataSource<ICruisePorts>()
  @ViewChild('TablePorts') TablePorts = new MatSort();
  

  constructor(public dialog: MatDialog, 
    private cruisesService: CruisesService, 
    public portService: PortsService,
    public snackBarMessageService: SnackBarMessageService
    ) { }
  
  ngOnInit(): void {     
    this.dataSourcePorts.data = this.cruise.cruisePorts
    
    this.portService.getPorts().subscribe( () => {
      this.dataSourcePorts.data = this.cruise.cruisePorts;
    })
  }
  
  public openDialog(): void {
    
    const newCruisePort : ICruisePorts  = {
      id: -1,
      name: '',
      arrival_date: undefined,
      arrival_time: undefined,
      duration: undefined,
      port_id: -1
    }
    const data = {
      page_type: true,
      start_date: this.cruise.start_date,
      end_date: this.cruise.end_date,
      cruisePort: newCruisePort
    }
    if(this.isComplet) {
      const dialogRef = this.dialog.open(AddPortComponent, {
        data: data,
      });
  
      dialogRef.afterClosed().subscribe(result => {
        const condition = this.checkCondition(result);
        if(condition) {
          this.cruise.cruisePorts.push(result.cruisePort);
          this.dataSourcePorts.data = this.cruise.cruisePorts;
        }
      });
    } else {
      this.snackBarMessageService.openSnackBar("The cruise form is blank")

    }
   
  }

  public openDialogPort(): void {
    const dialogRef = this.dialog.open(PortMapComponent);
    dialogRef.afterClosed().subscribe(result => {
      if(result) {
        this.cruise.cruisePorts.push(result);
        this.dataSourcePorts.data = this.cruise.cruisePorts;
      }
    });
    
  }

  public deleteCruisePort(cruisePort:ICruisePorts)  {

    if(cruisePort.id === -1){
      const index = this.cruise.cruisePorts.indexOf(cruisePort);
      this.cruise.cruisePorts.splice(index, 1);
      this.dataSourcePorts.data = this.cruise.cruisePorts;
      this.snackBarMessageService.openSnackBar(" Successfully deleted");
    }
    else{
      this.cruisesService
      .deleteCruisePort(this.cruise.id, cruisePort.id)
      .subscribe(
        data => {
          const index = this.cruise.cruisePorts.indexOf(cruisePort);
          this.cruise.cruisePorts.splice(index, 1);
          this.dataSourcePorts.data = this.cruise.cruisePorts;
          this.snackBarMessageService.openSnackBar("Successfully  deleted");
        },
        error => this.snackBarMessageService.openSnackBar(error.message)
      )
    }
    
   
  }

  public checkCondition(result) : boolean{
    if(result.cruisePort.name ==='' || 
      result.cruisePort.arrival_date === undefined || 
      result.cruisePort.arrival_time === undefined ||
      result.cruisePort.duration === undefined
      ){
        this.snackBarMessageService.openSnackBar("Error:Blank Fields")
        return false;
    }
    this.snackBarMessageService.openSnackBar("All good")
    return true;
    
  }
  ngAfterViewInit() {    
    this.dataSourcePorts.sort = this.TablePorts;
  }
  
  public updateCruisePort(cruisePort: ICruisePorts){
    const modifiedCruisePort: ICruisePorts = {
      id: cruisePort.id,
      name: cruisePort.name,
      arrival_date: cruisePort.arrival_date,
      arrival_time: cruisePort.arrival_time,
      duration: cruisePort.duration,
      port_id: cruisePort.port_id,
    }
    const data = {
      page_type: false,
      start_date: this.cruise.start_date,
      end_date: this.cruise.end_date,
      cruisePort: modifiedCruisePort
    }
    
    if(this.isComplet) {
      const dialogRef = this.dialog.open(AddPortComponent, {
        data: data,
      });
  
      dialogRef.afterClosed().subscribe(result => {
        const condition = this.checkCondition(result);
        if(condition) {
          cruisePort.name = result.cruisePort.name
          cruisePort.arrival_date = result.cruisePort.arrival_date
          cruisePort.arrival_time = result.cruisePort.arrival_time
          cruisePort.duration = result.cruisePort.duration
          cruisePort.port_id = result.cruisePort.port_id
        }
      });
    }
    else {
      this.snackBarMessageService.openSnackBar("The cruise form is blank")

    }
    
  }
  
}
