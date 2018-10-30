import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

const endpoint = '/api/drinks/';
@Injectable({
  providedIn: 'root'
})
export class DrinkService {
  constructor(private http: HttpClient) {}

  getDrinks() {
    return [{ name: 'Vodka', value: 'Vodka' }, { name: 'Beer', value: 'Beer' }, { name: 'Wine', value: 'Wine' }];
  }

  getDrinkByAlcohol(alcohol: string) {
    return this.http.get(endpoint + 'cocktail/search/' + alcohol);
  }

  getDrinkById(id: string) {
    return this.http.get(endpoint + 'cocktail/search/id/' + id);
  }
}
