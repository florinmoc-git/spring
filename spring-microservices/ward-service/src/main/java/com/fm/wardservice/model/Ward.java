package com.fm.wardservice.model;

import java.util.ArrayList;
import java.util.List;

public class Ward {

    private long id;
    private String name;
    private List<Patient> patients = new ArrayList<>();

    public Ward() {
    }

    public Ward(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }


    @Override
    public String toString() {
        return "Ward{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patients=" + patients +
                '}';
    }
}
