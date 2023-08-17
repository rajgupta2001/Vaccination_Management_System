package com.example.VaccineManagementSystem.Controllers;

import com.example.VaccineManagementSystem.Dtos.RequestDtos.AssociateDocDto;
import com.example.VaccineManagementSystem.Models.Doctor;
import com.example.VaccineManagementSystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doc")
public class DoctorController {

    @Autowired
    private DoctorService docService;

    @PostMapping("/add")
    public String addDoc(@RequestBody Doctor doc){
        try{
            String res=docService.addDoc(doc);
            return res;
        } catch(Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/associateToCenter")
    public ResponseEntity<String> associateDoc(@RequestBody AssociateDocDto associateDocDto){
        try{
            String res=docService.associateDoc(associateDocDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
