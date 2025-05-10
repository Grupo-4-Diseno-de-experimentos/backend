package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;

@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long title;
    private String description;
    private String instructions;
    private Long calories;

    @ManyToOne
    @JoinColumn(name = "nutricionist_id")
    private Nutricionist nutricionist;

}
