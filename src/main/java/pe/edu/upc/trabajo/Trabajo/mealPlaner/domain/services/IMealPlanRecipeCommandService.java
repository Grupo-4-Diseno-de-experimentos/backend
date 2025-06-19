package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteMealPlanRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateMealPlanRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;

import java.util.Optional;

public interface IMealPlanRecipeCommandService {
    Optional<MealPlanRecipe> handle (CreateMealPlanRecipeCommand command);
    Optional<MealPlanRecipe> handle (UpdateMealPlanRecipeCommand command);
    void handle (DeleteMealPlanRecipeCommand command);
}
