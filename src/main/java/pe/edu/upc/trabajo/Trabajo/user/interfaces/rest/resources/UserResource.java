package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.typeObject.Role;

public record UserResource(
        Long id,
        String name,
        String lastname,
        String email,
        String password,
        Role role,
        String code,
        String description,
        String specialties,
        Long yearsExperience
) {
}
