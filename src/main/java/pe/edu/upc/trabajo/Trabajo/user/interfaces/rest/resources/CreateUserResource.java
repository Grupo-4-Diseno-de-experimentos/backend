package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.typeObject.Role;

public record CreateUserResource(String name,
                                 String lastName,
                                 String email,
                                 String password,
                                 Role role,
                                 String code,
                                 String description,
                                 String specialties,
                                 Long yearsExperience) {
}
