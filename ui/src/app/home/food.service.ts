import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

const endpoint = '/api/business/';
@Injectable({
  providedIn: 'root'
})
export class FoodService {
  constructor(private http: HttpClient) {}

  getRestaurants() {
    return this.http.get(endpoint + '/genres');
  }
}
