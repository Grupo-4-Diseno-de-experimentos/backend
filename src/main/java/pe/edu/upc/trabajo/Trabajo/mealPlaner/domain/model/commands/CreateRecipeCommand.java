package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

import java.util.List;

public record CreateRecipeCommand(String title,
                                  String description,
                                  String instructions,
                                  Long calories,
                                  Double carbs,
                                  Double protein,
                                  Double fats,
                                  List<Long> ingredientIds) {
    public CreateRecipeCommand {
        ingredientIds = ingredientIds == null ? List.of() : ingredientIds;
    }
}
