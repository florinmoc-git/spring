package healthinformationsystem.his.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Min(value = 0, message = "cannot be less than 0")
    private int weight;
    @Min(value = 0, message = "cannot be less than 0")
    @Max(value = 3, message = "cannot be more than 3")
    private double height;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate firstAdmissionDate;

    @OneToMany(targetEntity = Illness.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Set<Illness> illnesses = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "patient_allergies", joinColumns = @JoinColumn(name = "patient"))
    @Column(name = "allergy")
    private Set<String> allergies;
//    private Set<MedicalExamination> medicalExaminations;
    @Min(100)
    private int wardId;
    @Transient
    private long age;
    @PostLoad
    private void calculateAge(){
        this.age = ChronoUnit.YEARS.between(super.getBirthDate(), LocalDate.now());
    }

    public boolean addIllness(String illness){
       return illnesses.add(new Illness(illness, LocalDate.now()));
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

    public LocalDate getFirstAdmissionDate() {
        return firstAdmissionDate;
    }

    public void setFirstAdmissionDate(LocalDate firstAdmissionDate) {
        this.firstAdmissionDate = firstAdmissionDate;
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

    public void setAge(long age) {
        this.age = age;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }

//    public Set<MedicalExamination> getMedicalExaminations() {
//        return medicalExaminations;
//    }
//
//    public void setMedicalExaminations(Set<MedicalExamination> medicalExaminations) {
//        this.medicalExaminations = medicalExaminations;
//    }



    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", weightInKg=" + weight +
                ", heightInMetres=" + height +
                ", firstAdmissionDate=" + firstAdmissionDate +
                ", illnesses=" + illnesses +
//                ", ward=" + ward +
                ", age=" + age +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (weight != patient.weight) return false;
        if (Double.compare(patient.height, height) != 0) return false;
        if (wardId != patient.wardId) return false;
        if (age != patient.age) return false;
        if (!id.equals(patient.id)) return false;
        if (!firstAdmissionDate.equals(patient.firstAdmissionDate)) return false;
        if (!illnesses.equals(patient.illnesses)) return false;
        return allergies.equals(patient.allergies);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + weight;
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + firstAdmissionDate.hashCode();
        result = 31 * result + illnesses.hashCode();
        result = 31 * result + allergies.hashCode();
        result = 31 * result + wardId;
        result = 31 * result + (int) (age ^ (age >>> 32));
        return result;
    }
}
