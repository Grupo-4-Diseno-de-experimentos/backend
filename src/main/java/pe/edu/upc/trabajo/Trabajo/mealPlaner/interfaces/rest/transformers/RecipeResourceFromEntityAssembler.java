package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.RecipeResource;

public class RecipeResourceFromEntityAssembler {
    public static RecipeResource toResourceFromEntity(Recipe entity){
        return new RecipeResource(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getInstructions(), entity.getCalories(),entity.getMacros());
    }
}
