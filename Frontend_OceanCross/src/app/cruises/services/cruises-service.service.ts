import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({providedIn: "root"})
export class CruisesService {

  constructor(private http: HttpClient) { }

  public getCruises(): Observable<any> {
    return this.http.get('/api/cruises');
  }
  public getCruise(id: number): Observable<any> {
    return this.http.get(`/api/cruises/${id}`);
  }

}
