package com.example.VaccineManagementSystem.Services;

import com.example.VaccineManagementSystem.Exceptions.VaccCenterAddNotFound;
import com.example.VaccineManagementSystem.Models.VaccinationCenter;
import com.example.VaccineManagementSystem.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {

    @Autowired
    VaccinationCenterRepository vaccRepo;

    public String addCenter(VaccinationCenter vaccCenter) throws VaccCenterAddNotFound{
        if(vaccCenter.getAddress()==null) throw new VaccCenterAddNotFound("Center Address not found");
        vaccRepo.save(vaccCenter);
        return "Vaccination Center added at "+vaccCenter.getAddress();
    }
}
