```markdown
# Weather Dashboard Application

## Technology Stack:
- **Backend**: Spring Boot
- **Frontend**: Angular
- **API Fetch**: Weather data is fetched from the backend API.

## Branches:
- **main**: This branch contains the production-ready code.
- **development**: This branch is used for development and testing.
- **version-01**: This version only returns the current weather data (without a table or chart).
```
## Version 01:
![alt text](<screenshots/version 01/ui.png>)

## Backend:
### API Endpoint:
- **URL**: `http://localhost:8080/api/weather/city`

### Request Body (JSON):
```json
{
  "city": "Dhaka"
}
```

### Sample Response:
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
![Backend Response](<screenshots/version 01/backend.png>)

## Features:
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
   **Screenshot**: ![Weather Result](<screenshots/version 01/result.png>)

2. **Error Handling**:  
   If the city is not found or an error occurs, an error message will be displayed in both the frontend and console.  
   **Screenshot**: ![Error Example](<screenshots/version 01/error.png>)  
   **Console Error**: ![Console Error](<screenshots/version 01/console.png>)

3. **Validation**:  
   The application includes validation to prevent malicious attacks and ensure the integrity of input data.


