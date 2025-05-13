package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;

import java.util.Optional;

public interface IRecipeCommandService {
    Optional<Recipe> handle(CreateRecipeCommand command);
    Optional<Recipe> handle(UpdateRecipeCommand command);
    void handle (DeleteRecipeCommand command);
}
