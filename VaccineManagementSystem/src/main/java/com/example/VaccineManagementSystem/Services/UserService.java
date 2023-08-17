package com.example.VaccineManagementSystem.Services;

import com.example.VaccineManagementSystem.Dtos.RequestDtos.UpdateEmailDto;
import com.example.VaccineManagementSystem.Models.Dose;
import com.example.VaccineManagementSystem.Models.User;
import com.example.VaccineManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User addUser(User user){
        user=userRepo.save(user);
        return user;
    }

    public Date getVaccDate(Integer userId){
        User user=userRepo.findById(userId).get();
        Dose dose=user.getDose();
        return dose.getVaccineDate();
    }

    public String updateEmail(UpdateEmailDto updateEmailDto){
        int userId=updateEmailDto.getUserId();
        User user=userRepo.findById(userId).get();
        user.setEmail(updateEmailDto.getNewEmail()); //Modifying the entity
        userRepo.save(user); //.save() in JPA works as Save and Update both
        return "Email has been renewed with "+updateEmailDto.getNewEmail();
    }

    public User getByEmail(String email){
        User user=userRepo.findByEmail(email);
        return user;
    }
}
