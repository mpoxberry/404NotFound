import { FoodService } from './food.service';
import { Component, OnInit } from '@angular/core';
import { finalize } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { MovieService } from './movie.service';
import { DrinkService } from './drink.service';
import { AuthenticationService } from '@app/core/authentication/authentication.service';

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
  thirdFormGroup: FormGroup;
  foods: Object[];
  genres: GenreResponse[];
  drinks: Object[];
  movies: Object[];
  foodChoice: any;
  genreChoice: any;
  drinkChoice: any;
  isDisplayed = false;

  constructor(
    private _formBuilder: FormBuilder,
    private movieService: MovieService,
    private foodService: FoodService,
    private drinkService: DrinkService,
    private authenticationService: AuthenticationService
  ) {
    this.loadGenres();
    this.loadFoods();
    this.loadDrinks();
    this.loadMovies();
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      food: ['']
    });
    this.secondFormGroup = this._formBuilder.group({
      genre: ['']
    });
    this.thirdFormGroup = this._formBuilder.group({
      drink: ['']
    });
  }

  loadGenres() {
    this.movieService.getGenreList().subscribe((res: GenreResponse[]) => {
      this.genres = res['genres'];
    });
  }

  loadFoods() {
    this.foods = this.foodService.getRestaurants();
  }

  loadMovies() {
    this.movieService.getMovieList('28', '', 'popularity.desc').subscribe(res => {
      this.movies = res['results'];
      console.log(this.movies);
    });
  }

  loadDrinks() {
    this.drinks = this.drinkService.getDrinks();
  }
  firstFormGroupSubmit() {
    this.foodChoice = this.firstFormGroup.value.food;
    this.authenticationService.doTask(this.firstFormGroup.value.food);
  }
  secondFormGroupSubmit() {
    this.genreChoice = this.secondFormGroup.value.genre;
  }
  thirdFormGroupSubmit() {
    this.drinkChoice = this.thirdFormGroup.value.drink;
  }
}
