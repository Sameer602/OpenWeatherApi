package in.sameerit.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sameerit.entity.WeatherRecord;
import in.sameerit.repo.WeatherRecordRepository;
import in.sameerit.service.WeatherService;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherRecordRepository weatherRecordRepository;

    @GetMapping("/weather")
    public String getWeather(@RequestParam("postalCode") String postalCode, Model model) {
    	
        WeatherResponse weatherResponse = weatherService.getWeather(postalCode);

        WeatherRecord record = new WeatherRecord();
        record.setPostalCode(postalCode);
        record.setTemperature(weatherResponse.getMain().getTemp().toString());
        record.setDescription(weatherResponse.getWeather().get(0).getDescription());
        record.setTimestamp(LocalDateTime.now());

        weatherRecordRepository.save(record);

        model.addAttribute("weather", weatherResponse);
        model.addAttribute("history", weatherRecordRepository.findAll());

        return "weather";
    }
}

