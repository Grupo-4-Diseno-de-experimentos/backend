package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static RegisterUserCommand toCommandFromResource(CreateUserResource resource){
        return new RegisterUserCommand(resource.name(), resource.lastName(), resource.email(), resource.password(),resource.role(), resource.code(), resource.description(), resource.specialties(), resource.yearsExperience());
    }
}
