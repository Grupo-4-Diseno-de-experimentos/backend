package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record UpdateIngredientCommand(Long ingredientId,
                                      String name,
                                      Float quantity,
                                      Float calories,
                                      Float carbs,
                                      Float protein,
                                      Float fats,
                                      String category,
                                      Boolean available) {
}
