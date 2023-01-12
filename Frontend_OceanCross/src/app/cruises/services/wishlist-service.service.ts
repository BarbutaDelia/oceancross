import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IWishListPostDto } from '../models/wishlistPostDto.interface';

@Injectable({providedIn: "root"})
export class WishlistService {

  constructor(private http: HttpClient) { }

  public getCruises(user_id: number): Observable<any> {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const headers = new HttpHeaders( {
        Authorization: `${tokenParse.type} ${tokenParse.token}`,
    })
    return this.http.get(`/api/wishlist/${user_id}`,  {headers});
  }

  public addCruiseToWishlist(cruiseRequest: IWishListPostDto): Observable<any> {
    const tokenParse = JSON.parse(localStorage.getItem("data"))
    const headers = new HttpHeaders( {
        Authorization: `${tokenParse.type} ${tokenParse.token}`,
    })
    return this.http.post('/api/wishlist/', cruiseRequest, {headers});
  }

}
