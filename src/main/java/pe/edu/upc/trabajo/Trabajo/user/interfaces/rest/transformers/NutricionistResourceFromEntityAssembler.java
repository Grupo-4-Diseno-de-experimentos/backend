package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.CreateNutricionistResource;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.NutricionistResource;

public class NutricionistResourceFromEntityAssembler {
    public static NutricionistResource toResourceFromEntity(Nutricionist entity){
        return new NutricionistResource(entity.getId(), entity.getName(), entity.getLastName(), entity.getCode());
    }
}
