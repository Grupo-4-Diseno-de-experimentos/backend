package pe.edu.upc.trabajo.Trabajo.user.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.typeObject.Role;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Nutricionist nutricionist;

    public User(){

    }
    public User(Long id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public User(RegisterUserCommand command){
        this.name = command.name();;
        this.lastName = command.lastName();
        this.email = command.email();
        this.password = command.password();
        this.role = command.role();
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Nutricionist getNutricionist() {
        return nutricionist;
    }

    public void setNutricionist(Nutricionist nutricionist) {
        this.nutricionist = nutricionist;
    }
}
