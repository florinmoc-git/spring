package com.his.entities;

import com.his.enums.Sex;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "wards")
public class Ward {
    @Id
    private int id;
    private Sex patientSex;
    private int numberOfBeds;
    private int maxNumberOfBeds;
    private int occupancy;
//    @Formula("numberOfBeds - occupancy")
    @Transient
    private int freeBeds;
    @OneToMany
    private Set<Patient> patients;
//    @ManyToOne
//    @JoinColumn(name = "department_name")
//    private ClinicalDepartment department;
//
//    public ClinicalDepartment getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(ClinicalDepartment department) {
//        this.department = department;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sex getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(Sex patientSex) {
        this.patientSex = patientSex;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getMaxNumberOfBeds() {
        return maxNumberOfBeds;
    }

    public void setMaxNumberOfBeds(int maxNumberOfBeds) {
        this.maxNumberOfBeds = maxNumberOfBeds;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
