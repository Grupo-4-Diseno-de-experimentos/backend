package pe.edu.upc.trabajo.Trabajo.user.domain.services;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetUserByEmailAndPasswordQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IUserQueryService {
    Optional<User> handle(GetUserByIdQuery query);
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByEmailAndPasswordQuery query);

}
