package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources;

public record RecipeResource(Long id,
                             String title,
                             String description,
                             String instructions,
                             Long calories) {
}
