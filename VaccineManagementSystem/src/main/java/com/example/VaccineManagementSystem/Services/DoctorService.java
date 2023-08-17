package com.example.VaccineManagementSystem.Services;

import com.example.VaccineManagementSystem.Dtos.RequestDtos.AssociateDocDto;
import com.example.VaccineManagementSystem.Exceptions.CenterNotFound;
import com.example.VaccineManagementSystem.Exceptions.DocNotFound;
import com.example.VaccineManagementSystem.Exceptions.EmailEmptyException;
import com.example.VaccineManagementSystem.Exceptions.DocAlreadyExistsException;
import com.example.VaccineManagementSystem.Models.Doctor;
import com.example.VaccineManagementSystem.Models.VaccinationCenter;
import com.example.VaccineManagementSystem.Repository.DoctorRepository;
import com.example.VaccineManagementSystem.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository docRepo;

    @Autowired
    private VaccinationCenterRepository centerRepo;

    public String addDoc(Doctor doc) throws EmailEmptyException, DocAlreadyExistsException {
        //Validations - if statements
        if(doc.getEmail()==null) throw new EmailEmptyException("Email Compulsory");
        if(docRepo.findByEmail(doc.getEmail())!=null) throw new DocAlreadyExistsException("Doctor with this email exists");
        docRepo.save(doc);
        return "Doctor added";
    }

    public String associateDoc(AssociateDocDto associateDocDto) throws DocNotFound, CenterNotFound {
        Integer docId=associateDocDto.getDocId();
        Optional<Doctor> docOptional=docRepo.findById(docId);
        if(!docOptional.isPresent()) throw new DocNotFound("Doctor ID not found");
        Integer centerId=associateDocDto.getCenterId();
        Optional<VaccinationCenter> centerOptional=centerRepo.findById(centerId);
        if(!centerOptional.isPresent()) throw new CenterNotFound("Vaccination Center ID not found");

        Doctor doc=docOptional.get();
        VaccinationCenter center=centerOptional.get();
        doc.setVaccCenter(center); //Setting the foreign key

        //Setting bidirectional mapping
        center.getDocList().add(doc); //Adding doc to List of doc of VaccCenter

        centerRepo.save(center);
        return "Doctor has been associated with the Vaccination Center";
    }
}
