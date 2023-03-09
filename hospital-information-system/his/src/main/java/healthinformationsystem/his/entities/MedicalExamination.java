package healthinformationsystem.his.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class MedicalExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
//    private LocalDate date;
//    private String diagnosis;
//    private String examinationNotes;
//    @ManyToOne
//    private Set<Prescription> prescriptions;
//    private Set<Therapy> therapies;
//    private Set<Surgery> surgeries;
//
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
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public String getDiagnosis() {
//        return diagnosis;
//    }
//
//    public void setDiagnosis(String diagnosis) {
//        this.diagnosis = diagnosis;
//    }
//
//    public String getExaminationNotes() {
//        return examinationNotes;
//    }
//
//    public void setExaminationNotes(String examinationNotes) {
//        this.examinationNotes = examinationNotes;
//    }
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
}
