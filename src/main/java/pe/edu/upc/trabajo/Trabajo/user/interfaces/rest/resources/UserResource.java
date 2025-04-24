package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources;

public record UserResource(
        Long id,
        String name,
        String lastname,
        String email,
        String password
) {
}
