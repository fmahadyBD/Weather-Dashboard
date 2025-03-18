package com.fmahadybd.weather_dashboard_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmahadybd.weather_dashboard_service.response.WeatherData;

@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public WeatherData getWeatherByCity(String city) {
        try {
            // we extract location's data from another method
            JsonNode locationData = getCoordinates(city);
            if (locationData == null) {
                throw new RuntimeException("City not found or coordinates unavailable");
            }

            // get data lat,lon
            double latitude = locationData.get("lat").asDouble();
            double longitude = locationData.get("lon").asDouble();

            // complete the user for request
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                    "&longitude=" + longitude
                    + "&hourly=temperature_2m,wind_speed_10m,relative_humidity_2m,pressure_msl&timezone=auto";

            // make APi request
            JsonNode weatherResponse = restTemplate.getForObject(weatherUrl, JsonNode.class);

            // if we get then return it
            return extractWeatherData(weatherResponse);

        
        }
        // Classification of Excetion to handel proper error that I can degub it.
        catch (HttpClientErrorException e) {
            throw new RuntimeException("Error fetching weather data: " + e.getMessage());
        } catch (ResourceAccessException e) {
            throw new RuntimeException("Network error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected untime error: " + e.getMessage());
        }
    }

    private JsonNode getCoordinates(String city) {
        try {
            String geoUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + city;

            // make APi request
            JsonNode response = restTemplate.getForObject(geoUrl, JsonNode.class);

            // if get it then double check it 

            if (response != null && response.isArray() && response.size() > 0) {
                return response.get(0);
            }
            return null;
        }
         // Classification of Excetion to handel proper error that I can degub it.
        catch (HttpClientErrorException e) {
            throw new RuntimeException("Error fetching coordinates: " + e.getMessage());
        } catch (ResourceAccessException e) {
            throw new RuntimeException("Network error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }

    private WeatherData extractWeatherData(JsonNode weatherData) {
        if (weatherData == null || !weatherData.has("hourly")) {
            throw new RuntimeException("Invalid weather data received");
        }

        try {

            // I return huge data from the main APi. so we extract data with 1st min data from hourly
            JsonNode hourlyData = weatherData.get("hourly");
            double temperature = hourlyData.get("temperature_2m").get(0).asDouble();
            double humidity = hourlyData.get("relative_humidity_2m").get(0).asDouble();
            double windSpeed = hourlyData.get("wind_speed_10m").get(0).asDouble();
            double pressure = hourlyData.get("pressure_msl").get(0).asDouble();

            String classification = classifyTemperature(temperature);

            WeatherData weather = new WeatherData();
            weather.setTemperature(temperature);
            weather.setHumidity(humidity);
            weather.setWindSpeed(windSpeed);
            weather.setPressure(pressure);
            weather.setTemperatureClassification(classification);

            return weather;
        } catch (Exception e) {
            throw new RuntimeException("Error extracting weather data: " + e.getMessage());
        }
    }

    private String classifyTemperature(double temperature) {
        if (temperature < 10) {
            return "Cold";
        } else if (temperature < 25) {
            return "Cool";
        } else if (temperature < 29) {
            return "Warm";
        } else {
            return "Hot";
        }
    }

    public JsonNode getWeatherByCityName(String city) {
        try {
            String geoUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + city;
            ResponseEntity<String> geoResponse = restTemplate.getForEntity(geoUrl, String.class);
            JsonNode geoJson = objectMapper.readTree(geoResponse.getBody());

            if (geoJson.isEmpty()) {
                throw new RuntimeException("Invalid city name. Please enter a valid city.");
            }

            double latitude = geoJson.get(0).get("lat").asDouble();
            double longitude = geoJson.get(0).get("lon").asDouble();

           
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                    "&longitude=" + longitude +
                    "&hourly=temperature_2m&timezone=auto";

            ResponseEntity<String> weatherResponse = restTemplate.getForEntity(weatherUrl, String.class);
            JsonNode weatherJson = objectMapper.readTree(weatherResponse.getBody());

           
            JsonNode timeArray = weatherJson.get("hourly").get("time");
            JsonNode temperatureArray = weatherJson.get("hourly").get("temperature_2m");

            List<Map<String, String>> formattedData = new ArrayList<>();

            for (int i = 0; i < timeArray.size(); i++) {
                String dateTime = timeArray.get(i).asText(); 
                String date = dateTime.split("T")[0];
                String time = dateTime.split("T")[1];
                String temperature = temperatureArray.get(i).asText() + "°C";

                formattedData.add(Map.of(
                        "date", date,
                        "time", time,
                        "temperature", temperature
                ));
            }

            return objectMapper.valueToTree(formattedData);

        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather data: " + e.getMessage());
        }
    }

    

}