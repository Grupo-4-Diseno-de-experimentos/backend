package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.RecipeResource;

import java.util.Collections;
import java.util.List;

public class RecipeResourceFromEntityAssembler {
    public static RecipeResource toResourceFromEntity(Recipe entity){
        List<Long> ingredientIds = entity.getIngredients() != null
                ? entity.getIngredients().stream().map(Ingredient::getId).toList()
                : Collections.emptyList();
        return new RecipeResource(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getInstructions(), entity.getCalories(),entity.getMacros(),ingredientIds);
    }
}
