package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;

import java.util.List;

@Repository
public interface IMealPlanRecipeRepository extends JpaRepository<MealPlanRecipe,Long> {
    List<MealPlanRecipe> findByMealPlanId(Long mealPlanId);
}
