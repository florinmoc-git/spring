package healthinformationsystem.his.entities;

import healthinformationsystem.his.enums.Sex;
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
    private Set<Patient> patients;
    

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
}
