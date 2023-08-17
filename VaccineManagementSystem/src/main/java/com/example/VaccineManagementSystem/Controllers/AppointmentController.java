package com.example.VaccineManagementSystem.Controllers;

import com.example.VaccineManagementSystem.Dtos.RequestDtos.AppointmentReqDto;
import com.example.VaccineManagementSystem.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointService;

    @PostMapping("/book")
    public String bookAppoint(@RequestBody AppointmentReqDto appointmentReqDto){
        try{
            String res=appointService.bookAppoint(appointmentReqDto);
            return res;
        } catch(Exception e){
            return e.getMessage();
        }
    }
}
