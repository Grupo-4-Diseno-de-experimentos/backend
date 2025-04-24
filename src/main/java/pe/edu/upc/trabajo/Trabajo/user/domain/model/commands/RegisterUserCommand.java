package pe.edu.upc.trabajo.Trabajo.user.domain.model.commands;

public record RegisterUserCommand(String name,
                                  String lastName,
                                  String email,
                                  String password) {
}
