package com.example.VaccineManagementSystem.Services;

import com.example.VaccineManagementSystem.Models.Dose;
import com.example.VaccineManagementSystem.Models.User;
import com.example.VaccineManagementSystem.Repository.DoseRepository;
import com.example.VaccineManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepo;

    @Autowired
    UserRepository userRepo;

    public String giveDose(String doseId, Integer userId){

        User user=userRepo.findById(userId).get(); //Goal is to get user object

        Dose dose =new Dose(); //Entity of model created and saved in DB
        dose.setDoseId(doseId); //Setting its attribute
        dose.setUser(user);

        //For unidirectional mapping
        //doseRepo.save(dose); //Saving the entity

        //For bidirectional mapping
        user.setDose(dose); //Setting child obj in attribute
        userRepo.save(user); //Child will automatically be saved coz of cascading

        return "Dose given to User Successfully!";
    }
}
