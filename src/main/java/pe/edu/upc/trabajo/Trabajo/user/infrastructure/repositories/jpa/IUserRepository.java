package pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    Optional<User> searchByEmailAndPassword(String email, String password);
}
