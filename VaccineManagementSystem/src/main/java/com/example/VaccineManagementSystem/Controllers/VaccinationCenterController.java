package com.example.VaccineManagementSystem.Controllers;

import com.example.VaccineManagementSystem.Exceptions.VaccCenterAddNotFound;
import com.example.VaccineManagementSystem.Models.VaccinationCenter;
import com.example.VaccineManagementSystem.Services.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccCenter")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccService;

    //Professional way for coding in Spring Boot
    @PostMapping("/add")
    public ResponseEntity<String> addCenter(@RequestBody VaccinationCenter vaccCenter){
        try {
            String res = vaccService.addCenter(vaccCenter);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (VaccCenterAddNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
