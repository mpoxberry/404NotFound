import { Component, OnInit } from '@angular/core';
import { finalize } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MovieService } from './movie.service';

export interface GenreResponse {
  id: number;
  name: string;
}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  genres: GenreResponse[];
  movies: Object;

  constructor(private _formBuilder: FormBuilder, private movieService: MovieService) {
    this.loadGenres();
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

  loadGenres() {
    this.movieService.getGenreList().subscribe((res: GenreResponse[]) => {
      this.genres = res['genres'];
    });
  }

  loadMovies() {
    this.movieService.getMovieList('28', '', 'popularity.desc').subscribe(res => {
      this.movies = res['results'];
    });
  }
}
