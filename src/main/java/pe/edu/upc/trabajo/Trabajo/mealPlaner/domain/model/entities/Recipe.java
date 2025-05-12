package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;
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
    private String title;
    private String description;
    private String instructions;
    private Long calories;

    @ManyToOne
    @JoinColumn(name = "nutricionist_id")
    private Nutricionist nutricionist;

    public Recipe(){

    }

    public Recipe(Long id, String title, String description, String instructions, Long calories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.calories = calories;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructions() {
        return instructions;
    }

    public Long getCalories() {
        return calories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }
}
