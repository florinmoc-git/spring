package healthinformationsystem.his.entities;

import healthinformationsystem.his.enums.DoctorSpeciality;
import jakarta.persistence.*;

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
}
