package pe.edu.upc.trabajo.Trabajo.user.application.internal.commandServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.domain.services.IUserCommandService;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.IUserRepository;

import java.util.Optional;

@Service
public class UserCommandService implements IUserCommandService {

    private final IUserRepository userRepository;

    public UserCommandService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(RegisterUserCommand command) {
        Optional<User> existingUser = userRepository.searchByEmailAndPassword(command.email(), command.password());
        if (existingUser.isPresent()) {
            return Optional.empty();
        }
        User user = new User(command);
        try{
            var response = userRepository.save(user);
            return Optional.of(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteUserCommand command) {
        userRepository.deleteById(command.userId());
        System.out.println("User Delete");
    }
}
