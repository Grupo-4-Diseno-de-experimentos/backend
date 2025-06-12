package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

import java.util.List;

public record UpdateRecipeCommand(Long recipeId,
                                  String title,
                                  String description,
                                  String instructions,
                                  Long calories,
                                  Double carbs,
                                  Double protein,
                                  Double fats,
                                  List<Long> ingredientIds) {
    public UpdateRecipeCommand{
        ingredientIds = ingredientIds == null ? List.of() : ingredientIds;
    }
}
