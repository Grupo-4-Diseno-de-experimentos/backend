package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources;

import java.util.List;

public record CreateRecipeResource(String title,
                                   String description,
                                   String instructions,
                                   Long calories,
                                   Double carbs,
                                   Double protein,
                                   Double fats,
                                   List<Long> ingredientIds) {
}
