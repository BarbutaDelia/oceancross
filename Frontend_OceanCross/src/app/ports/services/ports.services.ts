import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ICoordonate } from 'src/app/map/models/coordinate.interface';
import { IPort } from '../interfaces/port.interface';

@Injectable({providedIn: "root"})
export class PortsService {

  public generalMapsCoordonates: BehaviorSubject<ICoordonate[]> = new BehaviorSubject([]);
  constructor(private http: HttpClient) { }

  public getPorts(): Observable<any> {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const headers = new HttpHeaders( {
        Authorization: `${tokenParse.type} ${tokenParse.token}`,
    })
    return this.http.get('/api/ports', {headers});
  }
  
  public getPort(id: number): Observable<any> {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const headers = new HttpHeaders( {
        Authorization: `${tokenParse.type} ${tokenParse.token}`,
    })
    return this.http.get(`/api/ports/${id}`, {headers});
  }

  public addPort(newPort: IPort): Observable<any> {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const headers = new HttpHeaders( {
        Authorization: `${tokenParse.type} ${tokenParse.token}`,
    })
    return this.http.post('/api/ports/', newPort, {headers});
  }

  public getCoordonates() {
    this.getPorts().subscribe((ports) => {
      this.convertPortToCoordinates(ports);
    })
  }

  public convertPortToCoordinates(ports: IPort[]) {
    const coordinates: ICoordonate[] = []
    ports.forEach((port) => {

      const cord :google.maps.LatLngLiteral = 
      {
        lat: Number(port.latitude),
        lng: Number(port.longitude)
      }
      const coordinate: ICoordonate = {
        coordinate: cord,
        portName: port.name
      }
      coordinates.push(coordinate);
    })
    this.generalMapsCoordonates.next(coordinates)

  }
}
