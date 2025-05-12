package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record UpdateMealPlanRecipeCommand(Long id,
                                          String day,
                                          String mealTime,
                                          Long recipeId,
                                          Long mealPlanId) {
}
