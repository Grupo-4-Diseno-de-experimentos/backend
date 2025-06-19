package pe.edu.upc.trabajo.Trabajo.user.domain.model.commands;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.typeObject.Role;

public record RegisterUserCommand(String name,
                                  String lastName,
                                  String email,
                                  String password,
                                  Role role,
                                  String code,
                                  String description,
                                  String specialties,
                                  Long yearsExperience) {
}
