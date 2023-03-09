package healthinformationsystem.his.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MedicalExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDate date;
    private String diagnosis;
    private String examinationNotes;

    @ManyToOne
    @JoinColumn(name = "performed_by_id")
    private Doctor performedBy;

    public Doctor getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(Doctor performedBy) {
        this.performedBy = performedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
