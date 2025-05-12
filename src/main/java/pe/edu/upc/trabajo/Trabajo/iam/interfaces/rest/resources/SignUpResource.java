package pe.edu.upc.trabajo.Trabajo.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String username, String password, List<String> roles) {
}
