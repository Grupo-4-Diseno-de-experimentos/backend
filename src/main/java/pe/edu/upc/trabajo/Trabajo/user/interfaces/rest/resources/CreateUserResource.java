package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources;

public record CreateUserResource(String name,
                                 String lastname,
                                 String email,
                                 String password) {
}
