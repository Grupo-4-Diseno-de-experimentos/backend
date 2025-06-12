package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

public record UpdateRecipeIngredientCommand(Long id, Long recipe_id, Long ingredient_id, Double quantity) {}
