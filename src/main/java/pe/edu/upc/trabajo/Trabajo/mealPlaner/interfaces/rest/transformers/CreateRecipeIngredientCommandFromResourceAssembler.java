package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeIngredientCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.CreateRecipeIngredientResource;

public class CreateRecipeIngredientCommandFromResourceAssembler {
    public static CreateRecipeIngredientCommand toCommandFromResource(CreateRecipeIngredientResource resource) {
        return new CreateRecipeIngredientCommand(
                resource.recipe_id(),
                resource.ingredient_id(),
                resource.quantity()
        );
    }
}
