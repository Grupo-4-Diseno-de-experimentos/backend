package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetAllIngredientsQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetIngredientByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IIngredientQueryService {
    Optional<Ingredient> handle(GetIngredientByIdQuery query);
    List<Ingredient> handle(GetAllIngredientsQuery query);
}
