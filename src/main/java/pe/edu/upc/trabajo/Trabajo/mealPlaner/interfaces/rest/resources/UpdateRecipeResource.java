package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources;

public record UpdateRecipeResource(String title,
                                   String description,
                                   String instructions,
                                   Long calories,
                                   Double carbs,
                                   Double protein,
                                   Double fats) {
}
