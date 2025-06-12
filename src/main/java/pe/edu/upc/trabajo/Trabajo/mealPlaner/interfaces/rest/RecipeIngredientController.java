package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.RecipeIngredientService;
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

    @GetMapping
    public List<RecipeIngredientResponse> getIngredientsByRecipeId(@RequestParam Long recipe_id) {
        return recipeService.getIngredientsByRecipeId(recipe_id);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateRecipeIngredientResource resource) {
        var command = CreateRecipeIngredientCommandFromResourceAssembler.toCommandFromResource(resource);
        var recipeIngredient = recipeService.create(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RecipeIngredientResponse(
                        recipeIngredient.getId(),
                        recipeIngredient.getRecipe().getId(),
                        recipeIngredient.getIngredient().getId(),
                        recipeIngredient.getQuantity()
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateRecipeIngredientResource resource) {
        var command = UpdateRecipeIngredientCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var updated = recipeService.update(command);

        return ResponseEntity.ok(new RecipeIngredientResponse(
                updated.getId(),
                updated.getRecipe().getId(),
                updated.getIngredient().getId(),
                updated.getQuantity()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        recipeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
