package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Day;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Mealtime;

public record UpdateMealPlanRecipeCommand(Long id,
                                          Day day,
                                          Mealtime mealTime,
                                          Long recipeId,
                                          Long mealPlanId) {
}
