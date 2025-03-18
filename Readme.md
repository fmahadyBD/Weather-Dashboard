
# Weather Dashboard Application

## Technology Stack:
- **Backend**: Spring Boot
- **Frontend**: Angular
- **API Fetch**: Weather data is fetched from the backend API.

## Branches:
- **main**: Contains the production-ready code.
- **development**: Used for development and testing.
- **version-01**: Returns only the current weather data (without a table or chart).
- **version-02**: Includes a table view of hourly weather data.
- **version-03**: Will include chart visualization (work in progress).


## Screenshots

### Version 01:
![alt text](<screenshots/version 01/ui.png>)

### Version 02:
![Version 02 Result](screenshots/version%2002/result.png)

---

# Project Details

## Version 01

### Backend:
#### API Endpoint:
- **URL**: `http://localhost:8080/api/weather/city`

#### Request Body (JSON):
```json
{
  "city": "Dhaka"
}
```

#### Sample Response:
```json
{
  "message": "Weather data fetched successfully",
  "status": true,
  "data": {
    "temperature": 26.6,
    "humidity": 78.0,
    "windSpeed": 4.0,
    "pressure": 1008.6,
    "temperatureClassification": "Warm"
  }
}
```

![Backend Response](screenshots/version%2001/backend.png)

### Features:
1. **Weather Data Fetching**:  
   Users can enter a city, and the system will fetch the current weather data for that city. The API returns the following structure:
   ```json
   {
     "status": "ok",
     "message": "string",
     "status": boolean,
     "data": {
       "temperature": number,
       "humidity": number,
       "windSpeed": number,
       "pressure": number,
       "temperatureClassification": string
     }
   }
   ```
   **Screenshot**: ![Weather Result](screenshots/version%2001/result.png)

2. **Error Handling**:  
   If the city is not found or an error occurs, an error message will be displayed in both the frontend and console.  
   **Screenshot**: ![Error Example](screenshots/version%2001/error.png)  
   **Console Error**: ![Console Error](screenshots/version%2001/console.png)

3. **Validation**:  
   The application includes validation to prevent malicious attacks and ensure the integrity of input data.

---

## Version 02

### Backend:
#### API Endpoint:
- **URL**: `http://localhost:8080/api/weather/full-data/city`

#### Request Body (JSON):
```json
{
  "city": "Dhaka"
}
```

#### Sample Response:
```json
{
  "message": "Weather data fetched successfully",
  "status": true,
  "data": [
    {
      "date": "2025-03-18",
      "time": "00:00",
      "temperature": "25.2°C"
    },
    {
      "date": "2025-03-18",
      "time": "01:00",
      "temperature": "24.8°C"
    },
    {
      "date": "2025-03-18",
      "time": "02:00",
      "temperature": "24.5°C"
    }
  ]
}
```

### Error Handling:
![Error Example](<screenshots/version 02/error.png>)

if any error happen, It will show

### Result:
![Version 02 Result](screenshots/version%2002/result.png)


---
