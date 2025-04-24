package pe.edu.upc.trabajo.Trabajo.user.domain.services;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.UpdateNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;

import java.util.Optional;

public interface INutricionistCommandService {
    Optional<Nutricionist> handle(RegisterNutricionistCommand command);
    Optional<Nutricionist> handle(UpdateNutricionistCommand command);
    void handle (DeleteNutricionistCommand command);
}
