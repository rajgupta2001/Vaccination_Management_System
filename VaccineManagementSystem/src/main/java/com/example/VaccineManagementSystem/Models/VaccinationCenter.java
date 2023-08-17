package com.example.VaccineManagementSystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vaccCenter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String centerName;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String address;
    private int doseCapacity;

    @OneToMany(mappedBy = "vaccCenter", cascade = CascadeType.ALL)
    private List<Doctor> docList=new ArrayList<>();
}
