package com.fmahadybd.weather_dashboard_service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double pressure;
    private String temperatureClassification;


}