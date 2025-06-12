package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources;

public record CreateRecipeIngredientResource(Long recipe_id, Long ingredient_id, Double quantity) {
}
