package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeIngredientCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeIngredientCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.RecipeIngredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IIngredientRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeIngredientRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.RecipeIngredientResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {

    private final IRecipeIngredientRepository recipeIngredientRepository;
    private final IRecipeRepository recipeRepository;
    private final IIngredientRepository ingredientRepository;

    public RecipeIngredientService(IRecipeIngredientRepository recipeIngredientRepository,
                                   IRecipeRepository recipeRepository,
                                   IIngredientRepository ingredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<RecipeIngredient> getRecipeIngredientsByRecipeId(Long recipeId) {
        return recipeIngredientRepository.findByRecipeId(recipeId);
    }
    public RecipeIngredient create(CreateRecipeIngredientCommand command) {
        var recipe = recipeRepository.findById(command.recipe_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));
        var ingredient = ingredientRepository.findById(command.ingredient_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found"));

        var recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantity(command.quantity());

        return recipeIngredientRepository.save(recipeIngredient);
    }

    public RecipeIngredient update(UpdateRecipeIngredientCommand command) {
        var recipeIngredient = recipeIngredientRepository.findById(command.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RecipeIngredient not found"));

        recipeIngredient.setQuantity(command.quantity());
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public void delete(Long id) {
        if (!recipeIngredientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "RecipeIngredient not found");
        }
        recipeIngredientRepository.deleteById(id);
    }

}
