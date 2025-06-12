package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.RecipeIngredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.RecipeIngredientResponse;

public class RecipeIngredientResponseAssembler {
    public static RecipeIngredientResponse toResourceFromEntity(RecipeIngredient recipeIngredient) {
        return new RecipeIngredientResponse(
                recipeIngredient.getId(),
                recipeIngredient.getRecipe().getId(),
                recipeIngredient.getIngredient().getId(),
                recipeIngredient.getQuantity()
        );
    }
}
