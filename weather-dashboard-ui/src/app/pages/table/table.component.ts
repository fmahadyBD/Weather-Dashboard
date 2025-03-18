import { Component } from '@angular/core';
import { WeatherService } from '../../service/weather.service';

@Component({
  selector: 'app-table',
  standalone: false,
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent {
  city: string = '';
  weatherData: any[] = [];
  errorMessage: string = '';

  constructor(private weatherService: WeatherService) {}

  fetchWeather() {
    if (!this.city.trim()) {
      this.errorMessage = 'Please enter a valid city name.';
      return;
    }
    
    this.weatherService.getWeather(this.city).subscribe({
      next: (response) => {
        if (response.status) {
          this.weatherData = response.data;
          this.errorMessage = '';
        } else {
          this.weatherData = [];
          this.errorMessage = response.data;
        }
      },
      error: (error) => {
        this.weatherData = [];
        this.errorMessage = 'Error fetching weather data. Please try again.';
      }
    });
  }
}
