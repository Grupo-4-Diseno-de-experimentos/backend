package pe.edu.upc.trabajo.Trabajo.user.domain.services;

import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetAllNutricionistsQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetNutricionistByIdQuery;

import java.util.List;
import java.util.Optional;

public interface INutricionistQueryService {
    Optional<Nutricionist> handle(GetNutricionistByIdQuery query);
    List<Nutricionist> handle(GetAllNutricionistsQuery query);

}
