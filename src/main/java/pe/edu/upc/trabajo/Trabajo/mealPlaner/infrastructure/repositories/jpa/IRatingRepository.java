package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Rating;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByMealPlanIdAndUserId(Long mealPlanId, Long userId);
    List<Rating> findByMealPlanId(Long mealPlanId);
}
