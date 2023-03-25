package healthinformationsystem.his.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "medical_examinations")
public class MedicalExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false, updatable = false)
    private LocalDateTime firstCreated;
    private LocalDateTime lastModified;
    @NotNull
    @Column(nullable = false)
    private String diagnosis;
    private String examinationNotes;
    @PrePersist
    public void prePersist() {
        this.firstCreated = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }

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

    public LocalDateTime getFirstCreated() {
        return firstCreated;
    }

    public void setFirstCreated(LocalDateTime firstCreated) {
        this.firstCreated = firstCreated;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

}

