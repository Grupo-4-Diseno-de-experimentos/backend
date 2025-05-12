package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.UpdateMealPlanerResource;

public class UpdateMealPlanerCommandFromResourceAssembler {
    public static UpdateMealPlanCommand toCommandFromResource(Long id, UpdateMealPlanerResource resource){
        return new UpdateMealPlanCommand(id, resource.name(), resource.category(), resource.description(), resource.goal(), resource.min_bmi(), resource.max_bmi(), resource.min_age(), resource.max_age(), resource.calories_per_d());
    }
}
