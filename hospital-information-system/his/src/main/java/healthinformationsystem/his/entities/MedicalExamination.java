package healthinformationsystem.his.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "medical_examinations")
public class MedicalExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate date;
    @NotNull
    @Column(nullable = false)
    private String diagnosis;
    private String examinationNotes;
    // NOTE A patient must first exist in order for a medical examination to be associated with that patient
    @NotNull
    @Column(nullable = false)
    private int patientId;
//    @ManyToOne
//    private Set<Prescription> prescriptions;
//    private Set<Therapy> therapies;
//    private Set<Surgery> surgeries;

//    @ManyToOne
//    @JoinColumn(name = "performed_by_id")
//    private Doctor performedBy;
//
//    public Doctor getPerformedBy() {
//        return performedBy;
//    }
//
//    public void setPerformedBy(Doctor performedBy) {
//        this.performedBy = performedBy;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getExaminationNotes() {
        return examinationNotes;
    }

    public void setExaminationNotes(String examinationNotes) {
        this.examinationNotes = examinationNotes;
    }
//
//    public Set<Therapy> getTherapies() {
//        return therapies;
//    }
//
//    public void setTherapies(Set<Therapy> therapies) {
//        this.therapies = therapies;
//    }
//
//    public Set<Surgery> getSurgeries() {
//        return surgeries;
//    }
//
//    public void setSurgeries(Set<Surgery> surgeries) {
//        this.surgeries = surgeries;
//    }
//
//    public Set<Prescription> getPrescriptions() {
//        return prescriptions;
//    }
//
//    public void setPrescriptions(Set<Prescription> prescriptions) {
//        this.prescriptions = prescriptions;
//    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
