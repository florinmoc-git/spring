package healthinformationsystem.his.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Illness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
//    private String name;
//    private LocalDate dateDiagnosed;
//
//
//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    private Patient patient;
//
//    public Patient getPatient() {
//        return patient;
//    }
//
//    public void setPatient(Patient patient) {
//        this.patient = patient;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public LocalDate getDateDiagnosed() {
//        return dateDiagnosed;
//    }
//
//    public void setDateDiagnosed(LocalDate dateDiagnosed) {
//        this.dateDiagnosed = dateDiagnosed;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
