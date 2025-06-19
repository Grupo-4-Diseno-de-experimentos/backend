package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetMealPlanByIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetMealPlanRecipesByPlanIdQuery;

import java.util.List;
import java.util.Optional;

public interface IMealPlanRecipeQueryService {
    Optional<List<MealPlanRecipe>> handle(GetMealPlanRecipesByPlanIdQuery query);
}
