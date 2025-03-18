import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { weatherResponse } from '../model/weatherResponse.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  private baseUrl = 'http://localhost:8080/api/weather'; 

  constructor(private http: HttpClient) { }

  getCurrentWeather(city: string): Observable<weatherResponse> {
    const url = `${this.baseUrl}/city`;
    return this.http.post<weatherResponse>(url, { city });
  }

  getWeather(city: string): Observable<any> {
    const url = `${this.baseUrl}/full-data/city`;
    return this.http.post<any>(url, { city });
  }
}
