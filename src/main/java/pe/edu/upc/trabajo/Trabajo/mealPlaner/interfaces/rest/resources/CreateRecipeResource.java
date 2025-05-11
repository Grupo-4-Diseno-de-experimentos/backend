package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources;

public record CreateRecipeResource(String title,
                                   String description,
                                   String instructions,
                                   Long calories) {
}
