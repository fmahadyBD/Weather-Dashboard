import { Component, OnInit } from '@angular/core';
import { weatherResponse } from '../../model/weatherResponse.model';
import { WeatherService } from '../../service/weather.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  
  
  cityForm: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;
  weather: any = null;
  selectedCity: string = '';

  constructor(
    private weatherService: WeatherService,
    private formBuilder: FormBuilder
  ) {
    this.cityForm = this.formBuilder.group({
      city: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.cityForm.invalid) {
      this.errorMessage = "Please provide a city name";
      return;
    }

    this.selectedCity = this.cityForm.value.city;
    this.weatherService.getCurrentWeather(this.selectedCity).subscribe({
      next: (response) => {
        this.weather = response;
        this.errorMessage = null;
        this.successMessage = "Weather data fetched successfully";
      },
      error: () => {
        this.errorMessage = 'Not Found this city';
        this.successMessage = null;
      }
    });
  }
}




