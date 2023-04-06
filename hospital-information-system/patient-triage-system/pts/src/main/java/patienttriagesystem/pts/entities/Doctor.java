package patienttriagesystem.pts.entities;

import jakarta.persistence.*;
import patienttriagesystem.pts.enums.DoctorSpeciality;

@Entity
public class Doctor extends Staff{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private DoctorSpeciality speciality;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DoctorSpeciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(DoctorSpeciality speciality) {
        this.speciality = speciality;
    }
}
