package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Macros;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;

import java.util.ArrayList;
import java.util.List;

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

    @Embedded
    private Macros macros;

    @ManyToMany
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;

    public Recipe(){

    }

    public Recipe(Long id, String title, String description, String instructions, Long calories, Macros macros,List<Ingredient> ingredients) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.calories = calories;
        this.macros = macros;
        this.ingredients = ingredients;

    }

    public Recipe(CreateRecipeCommand command, List<Ingredient> ingredients){
        this.title = command.title();
        this.description = command.description();
        this.instructions = command.instructions();
        this.calories = command.calories();
        this.macros = new Macros(command.carbs(), command.protein(), command.fats());
        this.ingredients = ingredients;
    }
    public void updateRecipeCommand(UpdateRecipeCommand command, List<Ingredient> ingredients){
        this.title = command.title();
        this.description = command.description();
        this.instructions = command.instructions();
        this.calories = command.calories();
        this.macros = new Macros(command.carbs(), command.protein(), command.fats());
        this.ingredients = new ArrayList<>(ingredients);
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

    public Macros getMacros() {
        return macros;
    }

    public void setMacros(Macros macros) {
        this.macros = macros;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
