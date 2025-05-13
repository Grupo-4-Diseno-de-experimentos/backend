package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetAllIngredientsQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetIngredientByIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IIngredientQueryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IIngredientQueryService ingredientQueryService;

    public IngredientController(IIngredientQueryService ingredientQueryService) {
        this.ingredientQueryService = ingredientQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        GetIngredientByIdQuery query = new GetIngredientByIdQuery(id);
        Optional<Ingredient> ingredient = ingredientQueryService.handle(query);
        return ingredient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        GetAllIngredientsQuery query = new GetAllIngredientsQuery();
        return ingredientQueryService.handle(query);
    }
}
