package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record AddRecipeToFavoriteCommand(Long userId, Long recipeId) {
}
