package pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface INutricionistRepository extends JpaRepository<Nutricionist,Long> {
    Optional<Nutricionist> findByCode(String code);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.nutricionist")
    List<User> findAllUsersWithNutricionist();
}
