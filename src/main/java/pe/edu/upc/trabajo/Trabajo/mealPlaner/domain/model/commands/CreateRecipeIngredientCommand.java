package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record CreateRecipeIngredientCommand(Long recipe_id, Long ingredient_id, Double quantity) {
}
