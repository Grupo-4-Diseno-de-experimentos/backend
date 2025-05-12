package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.commandServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IRecipeCommandService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;

import java.util.Optional;

@Service
public class RecipeCommandService implements IRecipeCommandService {

    private final IRecipeRepository recipeRepository;
    public RecipeCommandService(IRecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Optional<Recipe> handle(CreateRecipeCommand command) {
        Recipe recipe = new Recipe(command);

        try {
            Recipe saved = recipeRepository.save(recipe);
            return Optional.of(saved);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Recipe> handle(UpdateRecipeCommand command) {
        Optional<Recipe> existing = recipeRepository.findById(command.recipeId());

        if (existing.isEmpty()) return Optional.empty();

        Recipe recipe = existing.get();
        recipe.updateRecipeCommand(command);

        try {
            Recipe updated = recipeRepository.save(recipe);
            return Optional.of(updated);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void handle(DeleteRecipeCommand command) {
        recipeRepository.deleteById(command.recipeId());

    }
}
