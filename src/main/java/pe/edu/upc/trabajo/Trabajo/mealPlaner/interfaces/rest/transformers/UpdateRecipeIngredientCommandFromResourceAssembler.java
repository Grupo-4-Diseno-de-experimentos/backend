package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeIngredientCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.UpdateRecipeIngredientResource;

public class UpdateRecipeIngredientCommandFromResourceAssembler{
    public static UpdateRecipeIngredientCommand toCommandFromResource(Long id, UpdateRecipeIngredientResource resource) {
        return new UpdateRecipeIngredientCommand(
                id,
                resource.quantity()
        );
    }
}
