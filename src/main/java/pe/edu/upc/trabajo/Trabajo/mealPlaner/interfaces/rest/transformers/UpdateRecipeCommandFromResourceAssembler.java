package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.UpdateRecipeResource;

public class UpdateRecipeCommandFromResourceAssembler {
    public static UpdateRecipeCommand toCommandFromResource(Long id, UpdateRecipeResource resource){
        return new UpdateRecipeCommand(id,resource.title(),resource.description(),resource.instructions(),resource.calories(), resource.carbs(), resource.protein(), resource.fats());
    }
}
