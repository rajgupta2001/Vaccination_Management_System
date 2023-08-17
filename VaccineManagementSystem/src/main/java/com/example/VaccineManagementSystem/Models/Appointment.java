package com.example.VaccineManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date appointmentDate;
    private LocalTime appointmentTime;

    @ManyToOne
    @JoinColumn
    private Doctor doc;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;
}
