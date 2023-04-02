package patienttriagesystem.pts.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "patient_illnesses")
public class Illness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @NotNull
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateDiagnosed;

//    private Set<Prescription> prescriptions;
//    private Set<Therapy> therapies;
//    private Set<Surgery> surgeries;

    public Illness() {
    }

    public Illness(String name, LocalDate dateDiagnosed) {
        this.name = name;
        this.dateDiagnosed = dateDiagnosed;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Illness illness = (Illness) o;

        if (!Objects.equals(id, illness.id)) return false;
        if (!Objects.equals(name, illness.name)) return false;
        return Objects.equals(dateDiagnosed, illness.dateDiagnosed);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateDiagnosed != null ? dateDiagnosed.hashCode() : 0);
        return result;
    }
}
