package pe.edu.upc.trabajo.Trabajo.user.application.internal.commandServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.RegisterNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.UpdateNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.services.INutricionistCommandService;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.INutricionistRepository;

import java.util.Optional;

@Service
public class NutricionistCommandService implements INutricionistCommandService {

    private final INutricionistRepository nutricionistRepository;

    public NutricionistCommandService(INutricionistRepository nutricionistRepository) {
        this.nutricionistRepository = nutricionistRepository;
    }

    @Override
    public Optional<Nutricionist> handle(RegisterNutricionistCommand command) {
        Nutricionist nutricionist = new Nutricionist(command);
        try {
            var response = nutricionistRepository.save(nutricionist);
            return Optional.of(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Nutricionist> handle(UpdateNutricionistCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteNutricionistCommand command) {
        nutricionistRepository.deleteById(command.nutricionistId());
    }
}
