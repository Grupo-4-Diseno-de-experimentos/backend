package pe.edu.upc.trabajo.Trabajo.user.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "nutricionist")
public class Nutricionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String code;

    public Nutricionist(Long id, String name, String lastName, String code) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.code = code;
    }
}
