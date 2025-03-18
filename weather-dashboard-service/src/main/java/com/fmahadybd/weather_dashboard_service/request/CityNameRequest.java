package com.fmahadybd.weather_dashboard_service.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityNameRequest {


    /*
     * It will be make safe my appliaction  from injection and corss site scripting attack.
     */
    @NotBlank(message = "City name cannot be empty")
    @Pattern(
        regexp = "^[a-zA-Z\\s-]+$", 
        message = "City name must contain only letters, spaces, or hyphens"
    )
    private String city;
    
}
