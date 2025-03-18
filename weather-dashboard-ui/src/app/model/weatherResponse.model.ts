export interface weatherResponse{
    message: string;
    status: boolean;
    data: {
      temperature: number;
      humidity: number;
      windSpeed: number;
      pressure: number;
      temperatureClassification: string;
    };
}