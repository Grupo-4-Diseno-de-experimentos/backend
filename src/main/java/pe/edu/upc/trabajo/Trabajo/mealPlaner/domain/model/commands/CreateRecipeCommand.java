package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record CreateRecipeCommand(String title,
                                  String description,
                                  String instructions,
                                  Long calories) {
}
