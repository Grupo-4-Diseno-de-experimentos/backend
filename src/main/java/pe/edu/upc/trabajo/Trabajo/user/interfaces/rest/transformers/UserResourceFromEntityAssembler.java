package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

    public static UserResource toResourceFromEntity(User user, Nutricionist nutricionist) {
        return new UserResource(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),

                nutricionist != null ? nutricionist.getCode() : null,
                nutricionist != null ? nutricionist.getDescription() : null,
                nutricionist != null ? nutricionist.getSpecialties() : null,
                nutricionist != null ? nutricionist.getYearsExperience() : null
        );
    }

    // Si solo tienes User, y quieres obtener Nutricionist desde User:
    public static UserResource toResourceFromUser(User user) {
        Nutricionist nutricionist = user.getNutricionist(); // si tienes método en User
        return toResourceFromEntity(user, nutricionist);
    }
}
