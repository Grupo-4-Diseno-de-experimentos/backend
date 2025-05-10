package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record UpdateRecipeCommand(Long recipeId,
                                  String title,
                                  String description,
                                  String instructions,
                                  Long calories,
                                  Long nutricionistId) {
}
