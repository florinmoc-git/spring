package healthinformationsystem.his.entities;

import jakarta.persistence.*;

import java.util.Set;

@MappedSuperclass
public abstract class Department {

    @Id
    private String name;
    private String description;
    private Set<Staff> staff;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
