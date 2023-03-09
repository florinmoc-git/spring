package healthinformationsystem.his.entities;

import healthinformationsystem.his.entities.embedables.Address;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "street_no"))
    private Address address;
    private String phone;
//    This doesn't seem to work: creates duplicate entries. Bug reports exist.
//    @Cascade({
//            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//            org.hibernate.annotations.CascadeType.MERGE,
//            org.hibernate.annotations.CascadeType.PERSIST,
//            org.hibernate.annotations.CascadeType.REFRESH
//    })
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "hospital__hospital_departments",
//            joinColumns = @JoinColumn(name = "hospital"),
//            inverseJoinColumns = @JoinColumn(name = "hospital_department")
//    )
//    private Set<Department> departments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public Set<Department> getDepartments() {
//        return departments;
//    }
//
//    public void setDepartments(Set<Department> departments) {
//        this.departments = departments;
//    }
}
