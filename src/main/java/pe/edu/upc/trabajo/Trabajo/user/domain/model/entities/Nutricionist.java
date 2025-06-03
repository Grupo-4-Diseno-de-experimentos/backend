package pe.edu.upc.trabajo.Trabajo.user.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterUserCommand;

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
    public Nutricionist(RegisterNutricionistCommand command){
        this.name = command.name();;
        this.lastName = command.lastName();
        this.code = command.code();
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

    public void setCode(String code) {
        this.code = code;
    }
}
