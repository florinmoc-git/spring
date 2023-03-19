package healthinformationsystem.his.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import healthinformationsystem.his.entities.embedables.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Person {

    private String title;
    @NotBlank(message = "cannot be empty")
    private String firstName;
    @NotBlank(message = "cannot be empty")
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthDate;
    @Pattern(regexp = "^\\d{11}$", message = "not a valid phone number")
    private String phone;
    @Email(message = "not a valid email address")
    private String email;
    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "street_no"))
    private Address address;
    @Transient
    @JsonSerialize
    private String fullName;
    @Column(nullable = false)
    private LocalDateTime firstCreated;
    private LocalDateTime lastModified;
    @PrePersist
    public void prePersist(){
        this.firstCreated = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        this.lastModified = LocalDateTime.now();
    }
    @PostLoad
    private void setFullName(){
        this.fullName = title + " " + firstName + " " + lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDateTime getFirstCreated() {
        return firstCreated;
    }

    private void setFirstCreated(LocalDateTime firstCreated) {
        this.firstCreated = firstCreated;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    private void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

}
