package healthinformationsystem.his.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

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
    private int weightInKg;
    private double heightInMetres;
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

    private int wardId;
    @Transient
    private long age;

    public boolean addIllness(String illness){
       return illnesses.add(new Illness(illness, LocalDate.now()));
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    @PostLoad
    private void calculateAge(){
        this.age = ChronoUnit.YEARS.between(super.getBirthDate(), LocalDate.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(int weightInKg) {
        this.weightInKg = weightInKg;
    }

    public double getHeightInMetres() {
        return heightInMetres;
    }

    public void setHeightInMetres(double heightInMetres) {
        this.heightInMetres = heightInMetres;
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
                ", weightInKg=" + weightInKg +
                ", heightInMetres=" + heightInMetres +
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

        if (weightInKg != patient.weightInKg) return false;
        if (Double.compare(patient.heightInMetres, heightInMetres) != 0) return false;
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
        result = 31 * result + weightInKg;
        temp = Double.doubleToLongBits(heightInMetres);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + firstAdmissionDate.hashCode();
        result = 31 * result + illnesses.hashCode();
        result = 31 * result + allergies.hashCode();
        result = 31 * result + wardId;
        result = 31 * result + (int) (age ^ (age >>> 32));
        return result;
    }
}
