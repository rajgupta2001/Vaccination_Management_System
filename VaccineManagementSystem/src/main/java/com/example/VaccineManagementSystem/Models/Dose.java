package com.example.VaccineManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="dose")
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Primary Key

    @Column(unique = true)
    private String doseId; //Unique Key

    @CreationTimestamp //Auto adding of date when entry made
    private Date vaccineDate;

    //Unidirectional Mapping
    @OneToOne //This is connection of tables: one to one, many to one
    @JoinColumn
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoseId() {
        return doseId;
    }

    public void setDoseId(String doseId) {
        this.doseId = doseId;
    }

    public Date getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(Date vaccineDate) {
        this.vaccineDate = vaccineDate;
    }
}
