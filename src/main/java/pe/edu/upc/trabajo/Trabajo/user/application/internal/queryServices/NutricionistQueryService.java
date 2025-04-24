package pe.edu.upc.trabajo.Trabajo.user.application.internal.queryServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetAllNutricionistsQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetNutricionistByIdQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.services.INutricionistQueryService;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.INutricionistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NutricionistQueryService implements INutricionistQueryService {
    private final INutricionistRepository nutricionistRepository;

    public NutricionistQueryService(INutricionistRepository nutricionistRepository) {
        this.nutricionistRepository = nutricionistRepository;
    }

    @Override
    public Optional<Nutricionist> handle(GetNutricionistByIdQuery query) {
        return nutricionistRepository.findById(query.nutricionistId());
    }

    @Override
    public List<Nutricionist> handle(GetAllNutricionistsQuery query) {
        return nutricionistRepository.findAll();
    }

    public Optional<Nutricionist> login(String code){
        return nutricionistRepository.findByCode(code);
    }
}
