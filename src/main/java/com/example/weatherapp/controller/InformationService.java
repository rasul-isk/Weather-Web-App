package com.example.weatherapp.controller;


import com.example.weatherapp.services.APIService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class InformationService {

    @GetMapping(value = "/api")
    public String displayData() {
        return APIService.getData();
    }
}