package com.example.VaccineManagementSystem.Controllers;

import com.example.VaccineManagementSystem.Dtos.RequestDtos.UpdateEmailDto;
import com.example.VaccineManagementSystem.Models.User;
import com.example.VaccineManagementSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/getVaccDate")
    public Date getVaccDate(@RequestParam("userId") Integer userId){
        return userService.getVaccDate(userId);
    }

    @PutMapping("/updateEmail")
    public String updateEmail(@RequestBody UpdateEmailDto updateEmailDto){
        return userService.updateEmail(updateEmailDto);
    }

    @GetMapping("/getByEmail/{email}")
    public User getByEmail(@PathVariable("email") String email){
        return userService.getByEmail(email);
    }
}
