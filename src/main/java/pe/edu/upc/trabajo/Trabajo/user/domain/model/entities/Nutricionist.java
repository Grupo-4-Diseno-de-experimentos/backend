package pe.edu.upc.trabajo.Trabajo.user.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "nutricionist")
public class Nutricionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String code;
    private String description;
    private String specialties;
    private Long yearsExperience;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Nutricionist(){
    }

    public Nutricionist(Long id, String name, String lastName, String code) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.code = code;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCode() {
        return code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public Long getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Long yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
