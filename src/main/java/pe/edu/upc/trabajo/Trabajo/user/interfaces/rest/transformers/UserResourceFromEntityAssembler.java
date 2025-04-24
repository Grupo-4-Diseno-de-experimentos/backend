package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(entity.getId(), entity.getName(), entity.getLastName(), entity.getEmail(), entity.getPassword());
    }
}
