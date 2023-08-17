package com.example.VaccineManagementSystem.Models;

import com.example.VaccineManagementSystem.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId;

    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn
    private VaccinationCenter vaccCenter;

    @OneToMany(mappedBy = "doc", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList=new ArrayList<>();

}
