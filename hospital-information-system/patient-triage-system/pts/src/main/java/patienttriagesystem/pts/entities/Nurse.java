package patienttriagesystem.pts.entities;

import jakarta.persistence.*;
import patienttriagesystem.pts.enums.NurseLevel;

@Entity
public class Nurse extends Staff{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private NurseLevel level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NurseLevel getLevel() {
        return level;
    }

    public void setLevel(NurseLevel level) {
        this.level = level;
    }
}
