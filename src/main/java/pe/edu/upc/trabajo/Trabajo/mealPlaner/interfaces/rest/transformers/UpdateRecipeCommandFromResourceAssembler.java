package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.UpdateRecipeResource;

import java.util.Collections;
import java.util.List;

public class UpdateRecipeCommandFromResourceAssembler {
    public static UpdateRecipeCommand toCommandFromResource(Long id, UpdateRecipeResource resource){
        System.out.println("ID recibido en assembler: " + id);
        return new UpdateRecipeCommand(id,resource.title(),resource.description(),resource.instructions(),resource.calories(), resource.macros().getCarbs(),resource.macros().getProtein(), resource.macros().getFats(),resource.ingredientIds());
    }
}
