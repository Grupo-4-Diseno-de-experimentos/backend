package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe,Long> {
}
