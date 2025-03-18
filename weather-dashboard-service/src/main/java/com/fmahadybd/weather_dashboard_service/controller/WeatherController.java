package com.fmahadybd.weather_dashboard_service.controller;

import com.fmahadybd.weather_dashboard_service.request.CityNameRequest;
import com.fmahadybd.weather_dashboard_service.response.ApiResponse;
import com.fmahadybd.weather_dashboard_service.response.WeatherData;
import com.fmahadybd.weather_dashboard_service.service.WeatherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/city")
    public ResponseEntity<ApiResponse> getWeatherResult(@Valid @RequestBody CityNameRequest cityNameRequest) {
       
       System.out.println("in controller");
        try {
            WeatherData weatherData = weatherService.getWeatherByCity(cityNameRequest.getCity());

            if (weatherData == null) {
                return ResponseEntity.badRequest().body(
                        new ApiResponse("City not found or weather data unavailable", false, null)
                );
            }

            return ResponseEntity.ok(
                    new ApiResponse("Weather data fetched successfully", true, weatherData)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse(e.getMessage(), false, null)
            );
        }
    }
}