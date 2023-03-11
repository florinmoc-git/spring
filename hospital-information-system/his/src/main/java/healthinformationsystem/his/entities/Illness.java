package healthinformationsystem.his.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patient_illnesses")
public class Illness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateDiagnosed;

    // TBC
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "patient_id", nullable=false, referencedColumnName = "id")
//    @JsonIgnore
//    private Patient patient;

//    public Patient getPatient() {
//        return patient;
//    }
//
//    public void setPatient(Patient patient) {
//        this.patient = patient;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateDiagnosed() {
        return dateDiagnosed;
    }

    public void setDateDiagnosed(LocalDate dateDiagnosed) {
        this.dateDiagnosed = dateDiagnosed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
