package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;

import java.util.Optional;

@Repository
public interface IMealPlanRepository extends JpaRepository<MealPlan,Long> {
    Optional<MealPlan> findByIdWithRecipes(Long mealPlanid);
}
