package patientmanagementsystem.pms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import patientmanagementsystem.pms.entities.embedables.Address;

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

    @Column(nullable = false, updatable = false)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @ReadOnlyProperty
    private LocalDateTime firstCreated;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
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

    public LocalDateTime getFirstCreated() {
        return firstCreated;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

}
