package com.fmahadybd.weather_dashboard_service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    
    private String message;
    private boolean status;
    private Object data;

}
