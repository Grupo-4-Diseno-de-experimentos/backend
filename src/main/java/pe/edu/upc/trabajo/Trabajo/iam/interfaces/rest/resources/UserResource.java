package pe.edu.upc.trabajo.Trabajo.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String username, List<String> roles) {
}
