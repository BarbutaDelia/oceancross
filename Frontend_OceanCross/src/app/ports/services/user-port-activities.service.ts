import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { IActivityRequest } from '../interfaces/activity-request.interface';

@Injectable({providedIn: "root"})
export class UserPortActivitiesService {
  constructor(private http: HttpClient) { }

    public getPortAndUserActivities(user_id:number, port_id:number,cruise_id: number): Observable<any> {
        const tokenParse = JSON.parse(localStorage.getItem("data"))
        const headers = new HttpHeaders( {
            Authorization: `${tokenParse.type} ${tokenParse.token}`,
        })
        return this.http.get(`api/port-activities/${user_id}/${port_id}/${cruise_id}`, {headers});
    }
  
    public addActivityToUser(request: IActivityRequest[]): Observable<any> {
        const tokenParse = JSON.parse(localStorage.getItem("data"))
        const headers = new HttpHeaders( {
            Authorization: `${tokenParse.type} ${tokenParse.token}`,
        })
        const body  =  {userPortActivitiesPostDtoList:request }
        console.log(body)
        return this.http.post(`api/port-activities/`, body, {headers});
    } 

    public deleteAllActivitiesFromUser(user_id: number, cruise_id: number): Observable<any> {
        const tokenParse = JSON.parse(localStorage.getItem("data"))
        const headers = new HttpHeaders( {
            Authorization: `${tokenParse.type} ${tokenParse.token}`,
        })
        return this.http.delete(`api/port-activities/${user_id}/${cruise_id}`,{headers});
    }

}
