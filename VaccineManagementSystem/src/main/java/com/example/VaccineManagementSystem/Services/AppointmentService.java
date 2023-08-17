package com.example.VaccineManagementSystem.Services;

import com.example.VaccineManagementSystem.Dtos.RequestDtos.AppointmentReqDto;
import com.example.VaccineManagementSystem.Exceptions.DocNotFound;
import com.example.VaccineManagementSystem.Exceptions.UserNotFound;
import com.example.VaccineManagementSystem.Models.Appointment;
import com.example.VaccineManagementSystem.Models.Doctor;
import com.example.VaccineManagementSystem.Models.User;
import com.example.VaccineManagementSystem.Repository.AppointmentRepository;
import com.example.VaccineManagementSystem.Repository.DoctorRepository;
import com.example.VaccineManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointRepo;

    @Autowired
    private DoctorRepository docRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

    public String bookAppoint(AppointmentReqDto appointmentReqDto)throws DocNotFound, UserNotFound {

        Optional<Doctor> docOptional=docRepo.findById(appointmentReqDto.getDocId());
        if(!docOptional.isPresent()) throw new DocNotFound("Doctor ID not found");
        Optional<User> userOptional=userRepo.findById(appointmentReqDto.getUserId());
        if(!userOptional.isPresent()) throw new DocNotFound("User ID not found");
        Doctor doc=docOptional.get();
        User user=userOptional.get();

        //Creating obj and setting its attribute
        Appointment appoint=new Appointment();
        appoint.setAppointmentDate(appointmentReqDto.getAppointmentDate());
        appoint.setAppointmentTime(appointmentReqDto.getAppointmentTime());
        appoint.setDoc(doc); //Setting foreign key
        appoint.setUser(user); //Setting foreign key

        //Method 1
        appoint=appointRepo.save(appoint); //Saving before so we can get PK of appointment table
        doc.getAppointmentList().add(appoint);
        user.getAppointmentList().add(appoint);
        docRepo.save(doc);
        userRepo.save(user);

        //Method 2
        /* appoint=appointRepo.save(appoint);
        doc.getAppointmentList().add(appoint);
        //Get appointId from List of last doc
        List<Appointment> appointList=doc.getAppointmentList();
        Appointment latestAppoint=appointList.get(appointList.size()-1);
        int id=latestAppoint.getId();
        appoint.setId(id);
        user.getAppointmentList().add(appoint);
        docRepo.save(doc);
        userRepo.save(user); */

        //Email sender to user
        String body="Hi! "+user.getName()+"\n"+"You've successfully booked an appointment on "+appoint.getAppointmentDate()+
                " at "+appoint.getAppointmentTime()+"\n"+"Your doctor is "+doc.getName()+
                "\n"+"Please reach at "+doc.getVaccCenter().getAddress();
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("springacciojob@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Appointment Confirmed!");
        message.setText(body);
        mailSender.send(message);

        return "Appointment Successfully Booked!";
    }
}
