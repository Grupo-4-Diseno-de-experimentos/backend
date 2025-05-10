package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record CreateIngredientCommand(String name,
                                      Float quantity,
                                      Float calories,
                                      Float carbs,
                                      Float protein,
                                      Float fats,
                                      String category,
                                      Boolean available) {
}
