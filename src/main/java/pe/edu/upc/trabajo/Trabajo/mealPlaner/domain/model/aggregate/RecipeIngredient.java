package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate;

import jakarta.persistence.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.RecipeIngredientId;

@Entity
@IdClass(RecipeIngredientId.class)
public class RecipeIngredient {
    @Id
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


}
