package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.queryServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetMealPlanRecipesByPlanIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IMealPlanRecipeQueryService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IMealPlansQueryService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MealPlanRecipeQueryService implements IMealPlanRecipeQueryService {

    private final IMealPlanRecipeRepository mealPlanRecipeRepository;

    public MealPlanRecipeQueryService(IMealPlanRecipeRepository mealPlanRecipeRepository) {
        this.mealPlanRecipeRepository = mealPlanRecipeRepository;
    }

    @Override
    public Optional<List<MealPlanRecipe>> handle(GetMealPlanRecipesByPlanIdQuery query) {
        return mealPlanRecipeRepository.findByMealPlanId(query.mealPlanId());
    }
}
