package pe.edu.upc.trabajo.Trabajo.user.application.internal.queryServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetUserByEmailAndPasswordQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.services.IUserQueryService;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryService implements IUserQueryService {
    private final IUserRepository userRepository;

    public UserQueryService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByEmailAndPasswordQuery query) {
        var userList = userRepository.findByEmailAndPassword(query.email(), query.password());
        if(userList.size() == 1){
            return Optional.of(userList.getFirst());
        }
        return userList.getFirst() == null ? Optional.empty() : Optional.of(userList.getFirst());
    }
}
