package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.queryServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetAllMealPlansQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetMealPlanByIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetMealPlanRecipesByPlanIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IMealPlansQueryService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MealPlanQueryService implements IMealPlansQueryService {
    private final IMealPlanRepository mealPlanRepository;

    public MealPlanQueryService(IMealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    @Override
    public Optional<MealPlan> handle(GetMealPlanByIdQuery query) {
        return mealPlanRepository.findById(query.id());
    }

    @Override
    public List<MealPlan> handle(GetAllMealPlansQuery query) {
        return mealPlanRepository.findAll();
    }

    @Override
    public Optional<MealPlan> handle(GetMealPlanRecipesByPlanIdQuery query) {
        return mealPlanRepository.findByIdWithRecipes(query.mealPlanId());
    }
}
