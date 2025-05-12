package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;

import java.util.Optional;

public interface IMealPlansCommandService {
    Optional<MealPlan> handle(CreateMealPlanCommand command);
    Optional<MealPlan> handle(UpdateMealPlanCommand command);
    void handle (DeleteMealPlanCommand command);
}
