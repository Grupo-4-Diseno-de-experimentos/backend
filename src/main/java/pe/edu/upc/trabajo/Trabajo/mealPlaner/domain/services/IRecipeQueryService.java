package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetAllRecipesQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetRecipeByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IRecipeQueryService {
    Optional<Recipe> handle(GetRecipeByIdQuery query);
    List<Recipe> handle(GetAllRecipesQuery query);
}
