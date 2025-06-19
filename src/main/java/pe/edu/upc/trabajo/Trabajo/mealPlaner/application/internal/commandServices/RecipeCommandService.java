package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.commandServices;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IRecipeCommandService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IIngredientRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeCommandService implements IRecipeCommandService {

    private final IRecipeRepository recipeRepository;
    private final IIngredientRepository ingredientRepository;

    public RecipeCommandService(IRecipeRepository recipeRepository, IIngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Optional<Recipe> handle(CreateRecipeCommand command) {
        try {
            List<Ingredient> ingredients = Optional.ofNullable(command.ingredientIds())
                    .orElse(List.of())
                    .stream()
                    .map(id -> ingredientRepository.findById(id)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente no encontrado: " + id)))
                    .toList();

            Recipe recipe = new Recipe(command, ingredients);
            recipe.setIngredients(ingredients);

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

        if (existing.isEmpty()) {
            return Optional.empty();
        }

        List<Ingredient> ingredients = Optional.ofNullable(command.ingredientIds())
                .orElse(List.of())
                .stream()
                .map(id -> {
                    return ingredientRepository.findById(id)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente no encontrado: " + id));
                })
                .toList();

        Recipe recipe = existing.get();

        recipe.updateRecipeCommand(command, new ArrayList<>(ingredients));

        try {
            Recipe updated = recipeRepository.save(recipe);
            return Optional.of(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void handle(DeleteRecipeCommand command) {
        recipeRepository.deleteById(command.recipeId());

    }
}