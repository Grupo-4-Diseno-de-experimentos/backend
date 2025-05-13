package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Macros;

public record RecipeResource(Long id,
                             String title,
                             String description,
                             String instructions,
                             Long calories,
                             Macros macros)
{
}
