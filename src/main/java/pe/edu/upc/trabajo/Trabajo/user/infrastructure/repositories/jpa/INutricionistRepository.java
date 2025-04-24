package pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;

@Repository
public interface INutricionistRepository extends JpaRepository<Nutricionist,Long> {
}
