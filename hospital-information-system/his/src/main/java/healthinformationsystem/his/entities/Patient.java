package healthinformationsystem.his.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private int weight;
    private LocalDate admissionDate;
    private Set<Ilness> ilness;

    @Transient
    private int age;

    @PostLoad
    private void calculateAge(){
//        this.age =
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
