import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { weatherResponse } from '../model/weatherResponse.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  private baseUrl = 'http://localhost:8080/api/weather/city'; 

  constructor(private http: HttpClient) { }

  getCurrentWeather(city: string): Observable<weatherResponse> {
    return this.http.post<weatherResponse>(this.baseUrl, { city });
  }
}
