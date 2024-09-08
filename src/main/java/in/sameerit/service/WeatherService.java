package in.sameerit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(String postalCode) {
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?zip=%s,us&units=imperial&appid=%s", postalCode, apiKey);
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}

