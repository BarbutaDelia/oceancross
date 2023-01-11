import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ICruiseRequest } from '../models/cruiseRequest.interface';

@Injectable({providedIn: "root"})
export class CruisesService {

  constructor(private http: HttpClient) { }

  public getCruises(): Observable<any> {
    return this.http.get('/api/cruises');
  }

  public getCruise(id: number): Observable<any> {
    return this.http.get(`/api/cruises/${id}`);
  }

  public addCruise(cruiseRequest: ICruiseRequest): Observable<any> {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const headers = new HttpHeaders( {
        Authorization: `${tokenParse.type} ${tokenParse.token}`,
    })
    return this.http.post('/api/cruises/', cruiseRequest, {headers});
  }
  public deleteCruise(id:number): Observable<any> {
    return this.http.delete(`/api/cruises/${id}`);
  }
  public updateCruise(id:number,cruiseRequest: ICruiseRequest ): Observable<any> {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const headers = new HttpHeaders( {
        Authorization: `${tokenParse.type} ${tokenParse.token}`,
    })
    return this.http.put(`/api/cruises/${id}`, cruiseRequest, {headers});
  }

  public deleteCruisePort(idCruise:number, idCruisePort:number): Observable<any> {
    return this.http.delete(`/api/cruises/${idCruise}/ports/${idCruisePort}`);
  }

  public deleteOnboardActivity(idCruise:number, idOnboardActivity:number): Observable<any> {
    return this.http.delete(`/api/cruises/${idCruise}/onboardActivities/${idOnboardActivity}`);
  }
  
  public getPortCruise(idCruise:number,idPort:number ): Observable<any> {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const headers = new HttpHeaders( {
        Authorization: `${tokenParse.type} ${tokenParse.token}`,
    })
    return this.http.get(`/api/cruises/${idCruise}/port/${idPort}`, {headers});
  }
}
