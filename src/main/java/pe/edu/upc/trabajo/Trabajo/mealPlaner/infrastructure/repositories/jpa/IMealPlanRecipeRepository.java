package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMealPlanRecipeRepository extends JpaRepository<MealPlanRecipe,Long> {
    Optional<List<MealPlanRecipe>> findByMealPlanId(Long mealPlanId);
}
