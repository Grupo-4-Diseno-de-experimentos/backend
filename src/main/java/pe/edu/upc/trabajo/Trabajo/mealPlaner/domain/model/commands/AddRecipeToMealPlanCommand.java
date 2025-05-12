package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record AddRecipeToMealPlanCommand(
        Long mealPlanId,
        Long recipeId,
        String day,
        String mealTime
) {
}
