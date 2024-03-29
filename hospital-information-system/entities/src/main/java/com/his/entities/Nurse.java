package com.his.entities;

import com.his.enums.NurseLevel;
import jakarta.persistence.*;

@Entity
public class Nurse extends Staff{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private NurseLevel level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NurseLevel getLevel() {
        return level;
    }

    public void setLevel(NurseLevel level) {
        this.level = level;
    }
}
