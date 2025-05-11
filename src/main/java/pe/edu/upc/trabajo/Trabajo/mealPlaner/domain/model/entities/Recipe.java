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

    public Recipe(){

    }

    public Recipe(Long id, Long title, String description, String instructions, Long calories, Nutricionist nutricionist) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.calories = calories;
        this.nutricionist = nutricionist;
    }
}
