package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.FavoriteRecipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFavoriteRepository extends JpaRepository<FavoriteRecipe, Long> {
    Optional<FavoriteRecipe> findByUserIdAndRecipeId(Long userId, Long recipeId);
    List<FavoriteRecipe> findAllByUserId(Long userId);
}
