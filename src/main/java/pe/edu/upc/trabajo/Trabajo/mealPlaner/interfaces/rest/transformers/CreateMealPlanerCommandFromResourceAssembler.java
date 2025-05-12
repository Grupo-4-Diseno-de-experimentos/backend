package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.CreateMealPlanerResource;

public class CreateMealPlanerCommandFromResourceAssembler {
    public static CreateMealPlanCommand toCommandFromResource(CreateMealPlanerResource resource){
        if (resource.goal() == null || resource.goal().isEmpty()) {
            throw new IllegalArgumentException("El campo 'goal' no puede ser nulo o vacío.");
        }
        Goal goal;
        try {
            goal = Goal.fromString(resource.goal());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor no válido para el campo 'goal'.");
        }
        return new CreateMealPlanCommand(resource.name(), resource.category(), resource.description(), goal,resource.min_bmi(), resource.max_bmi(), resource.min_age(), resource.max_age(), resource.calories_per_d());
    }
}
