package healthinformationsystem.his.entities;

import healthinformationsystem.his.enums.DepartmentType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "clinical_departments")
public class ClinicalDepartment extends Department{
//
//    private DepartmentType departmentType;
//    @ManyToOne
//    @JoinColumn(name = "head_doctor_id")
//    private Doctor headDoctor;
//    @ManyToOne
//    @JoinColumn(name = "head_nurse_id")
//    private Nurse headNurse;
//    @ManyToOne
//    private Set<Doctor> doctors;
//    private Set<Nurse> nurses;
//    private Set<Ward> ward;
//
//    public Nurse getHeadNurse() {
//        return headNurse;
//    }
//
//    public void setHeadNurse(Nurse headNurse) {
//        this.headNurse = headNurse;
//    }
//
//    public Doctor getHeadDoctor() {
//        return headDoctor;
//    }
//
//    public void setHeadDoctor(Doctor headDoctor) {
//        this.headDoctor = headDoctor;
//    }
//
//    public DepartmentType getDepartmentType() {
//        return departmentType;
//    }
//
//    public void setDepartmentType(DepartmentType departmentType) {
//        this.departmentType = departmentType;
//    }
//
//    public Set<Doctor> getDoctors() {
//        return doctors;
//    }
//
//    public void setDoctors(Set<Doctor> doctors) {
//        this.doctors = doctors;
//    }
//
//    public Set<Nurse> getNurses() {
//        return nurses;
//    }
//
//    public void setNurses(Set<Nurse> nurses) {
//        this.nurses = nurses;
//    }
//
//    public Set<Ward> getWard() {
//        return ward;
//    }
//
//    public void setWard(Set<Ward> ward) {
//        this.ward = ward;
//    }
}
