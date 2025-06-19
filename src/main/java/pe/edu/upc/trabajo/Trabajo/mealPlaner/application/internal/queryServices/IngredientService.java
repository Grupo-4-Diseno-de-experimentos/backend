package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.queryServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetAllIngredientsQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetIngredientByIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IIngredientQueryService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IIngredientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService implements IIngredientQueryService {

    private final IIngredientRepository ingredientRepository;

    public IngredientService(IIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public Optional<Ingredient> handle(GetIngredientByIdQuery query) {
        return ingredientRepository.findById(query.ingredientId());
    }

    @Override
    public List<Ingredient> handle(GetAllIngredientsQuery query) {
        return ingredientRepository.findAll();
    }
}
