package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.RecipeIngredientService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.RecipeIngredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.CreateRecipeIngredientResource;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.RecipeIngredientResponse;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.UpdateRecipeIngredientResource;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers.CreateRecipeIngredientCommandFromResourceAssembler;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers.RecipeIngredientResponseAssembler;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers.UpdateRecipeIngredientCommandFromResourceAssembler;

import java.util.List;

@RestController
@RequestMapping("/recipe_ingredients")
public class RecipeIngredientController {

    private final RecipeIngredientService recipeService;


    public RecipeIngredientController(RecipeIngredientService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RecipeIngredientResponse>> getRecipeIngredientsByRecipeId(@PathVariable Long id) {
        List<RecipeIngredient> recipeIngredients = recipeService.getRecipeIngredientsByRecipeId(id);
        List<RecipeIngredientResponse> responseList = recipeIngredients.stream()
                .map(RecipeIngredientResponseAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(responseList);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateRecipeIngredientResource resource) {
        var command = CreateRecipeIngredientCommandFromResourceAssembler.toCommandFromResource(resource);
        var recipeIngredient = recipeService.create(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RecipeIngredientResponseAssembler.toResourceFromEntity(recipeIngredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateRecipeIngredientResource resource) {
        var command = UpdateRecipeIngredientCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var updated = recipeService.update(command);

        return ResponseEntity.ok(RecipeIngredientResponseAssembler.toResourceFromEntity(updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        recipeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
