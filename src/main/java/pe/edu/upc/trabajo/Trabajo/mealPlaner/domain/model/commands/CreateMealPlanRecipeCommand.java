package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record CreateMealPlanRecipeCommand(
        String day,
        String mealTime,
        Long recipeId,
        Long mealPlanId
) {
}
