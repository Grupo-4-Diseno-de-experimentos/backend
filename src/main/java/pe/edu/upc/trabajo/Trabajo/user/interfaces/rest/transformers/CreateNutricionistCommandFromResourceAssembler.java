package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.CreateNutricionistResource;

public class CreateNutricionistCommandFromResourceAssembler {
    public static RegisterNutricionistCommand toCommandFromResource(CreateNutricionistResource resource){
        return new RegisterNutricionistCommand(resource.name(), resource.lastname(), resource.code());
    }
}
