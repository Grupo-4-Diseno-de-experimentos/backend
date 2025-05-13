package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.RecipeIngredientResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {

    private final IRecipeRepository recipeRepository;

    public RecipeIngredientService(IRecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeIngredientResponse> getIngredientsByRecipeId(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));

        return recipe.getIngredients().stream()
                .map(ingredient -> new RecipeIngredientResponse(ingredient.getId()))
                .collect(Collectors.toList());
    }

}
