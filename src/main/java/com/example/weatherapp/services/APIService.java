package com.example.weatherapp.services;

import org.springframework.web.client.RestTemplate;

public class APIService {
    public static String getData() {
        String url = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
        RestTemplate restTemplate = new RestTemplate();
        String result = result = restTemplate.getForObject(url, String.class);

        return result;
    }
}
