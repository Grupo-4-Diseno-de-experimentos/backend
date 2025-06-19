package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.queryServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetAllRecipesQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetRecipeByIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IRecipeQueryService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeQueryService implements IRecipeQueryService {

    private final IRecipeRepository recipeRepository;

    public RecipeQueryService(IRecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Optional<Recipe> handle(GetRecipeByIdQuery query) {
        return recipeRepository.findById(query.recipeId());
    }

    @Override
    public List<Recipe> handle(GetAllRecipesQuery query) {
        return recipeRepository.findAll();
    }
}
