package pe.edu.upc.trabajo.Trabajo.user.domain.services;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;

import java.util.Optional;

public interface IUserCommandService {
    Optional<User> handle (RegisterUserCommand command);
    Optional<User> handle (UpdateUserCommand command);
    void handle (DeleteUserCommand command);
}
