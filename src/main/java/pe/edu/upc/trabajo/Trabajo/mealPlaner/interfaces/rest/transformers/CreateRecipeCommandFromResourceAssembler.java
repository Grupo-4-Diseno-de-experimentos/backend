package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.CreateRecipeResource;

public class CreateRecipeCommandFromResourceAssembler {
    public static CreateRecipeCommand toCommandFromResource(CreateRecipeResource resource){
        return new CreateRecipeCommand(resource.title(), resource.description(), resource.instructions(), resource.calories(), resource.carbs(), resource.protein(), resource.fats(),resource.ingredientIds());
    }
}