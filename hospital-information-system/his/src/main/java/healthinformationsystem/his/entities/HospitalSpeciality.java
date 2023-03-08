package healthinformationsystem.his.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "hospital_specialities")
public class HospitalSpeciality {

    @Id
    private String hospitalSpeciality;
    private String description;

    public String getHospitalSpeciality() {
        return hospitalSpeciality;
    }

    public void setHospitalSpeciality(String hospitalSpeciality) {
        this.hospitalSpeciality = hospitalSpeciality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HospitalSpeciality{" +
                "hospitalSpeciality='" + hospitalSpeciality + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
