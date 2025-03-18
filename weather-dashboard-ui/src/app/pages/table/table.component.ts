import { Component, Input, SimpleChanges } from '@angular/core';
import { WeatherService } from '../../service/weather.service';

@Component({
  selector: 'app-table',
  standalone: false,
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent {
  
  
  @Input() city: string = '';  // recive
  weatherData: any[] = [];
  errorMessage: string = '';

  constructor(private weatherService: WeatherService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['city'] && this.city) {
      this.fetchWeather();
    }
  }

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
      error: () => {
        this.weatherData = [];
        this.errorMessage = 'Error fetching weather data. Please try again.';
      }
    });
  }


}
