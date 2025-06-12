package pe.edu.upc.trabajo.Trabajo.user.application.internal.commandServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.typeObject.Role;
import pe.edu.upc.trabajo.Trabajo.user.domain.services.IUserCommandService;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.INutricionistRepository;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.IUserRepository;

import java.util.Optional;

@Service
public class UserCommandService implements IUserCommandService {

    private final IUserRepository userRepository;
    private final INutricionistRepository nutricionistRepository;

    public UserCommandService(IUserRepository userRepository, INutricionistRepository nutricionistRepository) {
        this.userRepository = userRepository;
        this.nutricionistRepository = nutricionistRepository;
    }

    @Override
    public Optional<User> handle(RegisterUserCommand command) {
        Optional<User> existingUser = userRepository.searchByEmail(command.email());
        if (existingUser.isPresent()) {
            return Optional.empty();
        }

        User user = new User(command);
        try {
            var savedUser = userRepository.save(user);

            if (command.role() == Role.NUTRICIONIST) {
                Nutricionist nutricionist = new Nutricionist();
                nutricionist.setName(command.name());
                nutricionist.setLastName(command.lastName());
                nutricionist.setCode(command.code());
                nutricionist.setDescription(command.description());
                nutricionist.setSpecialties(command.specialties());
                nutricionist.setYearsExperience(command.yearsExperience());
                nutricionist.setUser(savedUser);
                nutricionistRepository.save(nutricionist);
            }

            return Optional.of(savedUser);
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
