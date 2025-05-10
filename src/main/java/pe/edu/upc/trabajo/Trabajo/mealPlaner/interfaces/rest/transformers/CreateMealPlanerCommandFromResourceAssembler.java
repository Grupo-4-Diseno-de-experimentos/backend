package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.CreateMealPlanerResource;

public class CreateMealPlanerCommandFromResourceAssembler {
    public static CreateMealPlanCommand toCommandFromResource(CreateMealPlanerResource resource){
        return new CreateMealPlanCommand(resource.name(), resource.category(), resource.description(), resource.goal(),resource.min_bmi(), resource.max_bmi(), resource.min_age(), resource.max_age(), resource.calories_per_d(), resource.nutricionistId());
    }
}
