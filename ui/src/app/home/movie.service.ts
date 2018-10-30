import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

const endpoint = '/api/movies/';
@Injectable({
  providedIn: 'root'
})
export class MovieService {
  constructor(private http: HttpClient) {}

  getGenreList() {
    return this.http.get(endpoint + '/genres');
  }

  getMovieList(genres: string, keywords: string, sort: string) {
    const params = new HttpParams()
      .set('with_genres', genres)
      .set('with_keywords', keywords)
      .set('sort_by', sort);
    return this.http.get(endpoint + '/search', { params: params });
  }

  getMovieImage(url: string) {
    return this.http.get(endpoint + '/image/' + url);
  }
}
