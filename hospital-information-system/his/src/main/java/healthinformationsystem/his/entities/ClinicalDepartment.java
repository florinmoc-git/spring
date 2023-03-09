package healthinformationsystem.his.entities;

import healthinformationsystem.his.enums.Departments;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Set;

@Entity
public class ClinicalDepartment extends Department{

    private Departments department;
    @ManyToOne
    @JoinColumn(name = "head_doctor_id")
    private Doctor headDoctor;
    @ManyToOne
    @JoinColumn(name = "head_nurse_id")
    private Nurse headNurse;
    private Set<Doctor> doctors;
    private Set<Nurse> nurses;
    private Set<Ward> ward;

    public Nurse getHeadNurse() {
        return headNurse;
    }

    public void setHeadNurse(Nurse headNurse) {
        this.headNurse = headNurse;
    }

    public Doctor getHeadDoctor() {
        return headDoctor;
    }

    public void setHeadDoctor(Doctor headDoctor) {
        this.headDoctor = headDoctor;
    }

}
