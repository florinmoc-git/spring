package healthinformationsystem.his.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import healthinformationsystem.his.entities.embedables.Address;
import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Person {

    private String title;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthDate;
    private String phone;
    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "street_no"))
    private Address address;
    @Transient
    private String fullName;
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

    @Override
    public String toString() {
        return "Person{" +
                "title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
