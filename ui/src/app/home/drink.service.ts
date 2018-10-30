import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

const endpoint = '/api/business/';
@Injectable({
  providedIn: 'root'
})
export class DrinkService {
  constructor(private http: HttpClient) {}

  getDrinks() {
    return [{ name: 'Vodka', value: 'Vodka' }, { name: 'Beer', value: 'Beer' }, { name: 'Soju', value: 'Soju' }];
  }
}
