import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MapInfoWindow, MapMarker } from '@angular/google-maps';
import { MatDialogRef } from '@angular/material/dialog';
import { IPort } from 'src/app/ports/interfaces/port.interface';
import { PortsService } from 'src/app/ports/services/ports.services';
import { SnackBarMessageService } from 'src/app/shared/services/snack-bar-message.service';

@Component({
  selector: 'app-port',
  templateUrl: './port.component.html',
  styleUrls: ['./port.component.css']
})
export class PortMapComponent implements OnInit {
  constructor ( 
    private snackBarService:SnackBarMessageService,
    private portService: PortsService,
    public dialogRef: MatDialogRef<PortMapComponent>,
    ) {}
    public portName: string = "";

  public ngOnInit(): void {}
  
  @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow | undefined;
  public center: google.maps.LatLngLiteral = { lat: 24, lng: 12};
  public markerPositions: google.maps.LatLngLiteral = { lat: -89.99921646541038, lng: 0};
  public zoom = 4;

  public addMarker(event: google.maps.MapMouseEvent) {
      if (event.latLng != null) {
        this.markerPositions = event.latLng.toJSON()
      }
  }
  
  public openInfoWindow(marker: MapMarker) {
      if (this.infoWindow != undefined) this.infoWindow.open(marker);
  }

  public onSubmit()
  {
    if(this.markerPositions.lat === -89.99921646541038 || this.markerPositions.lng === 0) {
      this.openSnackBar("A pin must be added to the map")
    }
    else if(this.portName === '') {
      this.openSnackBar("Add a port name")
    }
    else {
      const newPort: IPort = {
        name: this.portName,
        longitude: this.markerPositions.lng.toString(),
        latitude: this.markerPositions.lat.toString()
      }
      this.portService.addPort(newPort).subscribe(
        (data) => {
          this.openSnackBar(data.message); 
          this.portName = ''; 
          this.markerPositions = { lat:-89.99921646541038, lng: 0};
          this.portService.getCoordonates();
        },
        
        (error) => this.openSnackBar(error.message)
        )

    }
  }
  
  public openSnackBar(message: string) {
    this.snackBarService.openSnackBar(message)
  } 

  public onNoClick(): void {
    this.dialogRef.close();
  }

}
