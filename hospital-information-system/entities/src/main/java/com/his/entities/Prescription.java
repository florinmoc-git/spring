package com.his.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Set<String> medicines;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "prescribedby_id")
    private Doctor prescribedby;

    public Doctor getPrescribedby() {
        return prescribedby;
    }

    public void setPrescribedby(Doctor prescribedby) {
        this.prescribedby = prescribedby;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<String> medicines) {
        this.medicines = medicines;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
