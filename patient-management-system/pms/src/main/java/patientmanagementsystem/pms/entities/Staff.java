package patientmanagementsystem.pms.entities;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;
import java.util.Set;

@MappedSuperclass
public abstract class Staff extends Person {

    private LocalDate dateJoined;
    private Set<String> education;
    private Set<String> certifications;
    private Set<String> languages;

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Set<String> getEducation() {
        return education;
    }

    public void setEducation(Set<String> education) {
        this.education = education;
    }

    public Set<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(Set<String> certifications) {
        this.certifications = certifications;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }
}
