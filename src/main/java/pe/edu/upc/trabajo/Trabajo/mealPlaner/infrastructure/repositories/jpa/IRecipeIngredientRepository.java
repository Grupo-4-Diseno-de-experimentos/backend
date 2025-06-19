package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.RecipeIngredient;

import java.util.List;

@Repository
public interface IRecipeIngredientRepository extends JpaRepository<RecipeIngredient,Long> {
    List<RecipeIngredient> findByRecipeId(Long recipeId);
}
