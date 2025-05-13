package pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;

import java.util.Optional;

@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient,Long> {
    Optional<Ingredient> findByName(String name);
}
