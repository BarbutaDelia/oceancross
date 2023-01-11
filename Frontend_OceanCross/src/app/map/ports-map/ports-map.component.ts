import { Component, OnInit } from '@angular/core';
import { MapInfoWindow, MapMarker } from '@angular/google-maps';
import { PortsService } from 'src/app/ports/services/ports.services';
import { ICoordonate } from '../models/coordinate.interface';

@Component({
  selector: 'app-ports-map',
  templateUrl: './ports-map.component.html',
  styleUrls: ['./ports-map.component.css']
})
export class PortsMapComponent implements OnInit {
  public markerPositions: ICoordonate[];
  constructor ( private portService: PortsService) {}

  public ngOnInit(): void {
    this.portService.getCoordonates();
    this.portService.generalMapsCoordonates.subscribe((coordonates) => (
      this.markerPositions = coordonates
    ))
  }
  
  // @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow | undefined;
  public center: google.maps.LatLngLiteral = { lat: 24, lng: 12};
  public zoom = 2;
  
  public openInfoWindow(marker: MapMarker, infoWindow :MapInfoWindow ) {
      if (infoWindow != undefined){
        infoWindow.open(marker);
      } 
  }

}
