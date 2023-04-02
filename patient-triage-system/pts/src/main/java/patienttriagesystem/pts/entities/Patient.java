package patienttriagesystem.pts.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Transient
    private String fullName;
    @Min(value = 0, message = "cannot be less than 0")
    private int weight;
    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 3, message = "cannot be more than 3")
    private double height;
    @Min(100)
    private int wardId;
    @Transient
    private long age;

    @OneToMany(targetEntity = Illness.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Set<Illness> illnesses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
    @JsonManagedReference
    private Set<MedicalExamination> medicalExaminations;

    @ElementCollection
    @CollectionTable(name = "patient_allergies", joinColumns = @JoinColumn(name = "patient"))
    @Column(name = "allergy")
    private Set<String> allergies;

//    @PostLoad TODO - should be @PostLoad but for some reason it's not working.
    @PrePersist
    private void populateCalculatedFields(){
        this.age = ChronoUnit.YEARS.between(super.getBirthDate(), LocalDate.now());
        this.fullName = super.getTitle() + " " + super.getFirstName() + " " + super.getLastName();
    }

    public boolean addIllness(String illness){
        if(illnesses == null){
            illnesses = new HashSet<>();
        }
        return illnesses.add(new Illness(illness, LocalDate.now()));
    }
    public boolean addMedicalExamination(MedicalExamination medicalExamination){
        if(medicalExaminations == null){
            medicalExaminations = new HashSet<>();
        }
        return medicalExaminations.add(medicalExamination);
    }
    public boolean addAllergy(String allergy){
        if(allergies == null){
            allergies = new HashSet<>();
        }
        return allergies.add(allergy);
    }
    public String getFullName() {
        return fullName;
    }


    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Set<Illness> getIllnesses() {
        return illnesses;
    }

    public void setIllnesses(Set<Illness> illnesses) {
        this.illnesses = illnesses;
    }

    public long getAge() {
        return age;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }

    public Set<MedicalExamination> getMedicalExaminations() {
        return medicalExaminations;
    }

    public void setMedicalExaminations(Set<MedicalExamination> medicalExaminations) {
        this.medicalExaminations = medicalExaminations;
    }
}
