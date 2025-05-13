package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.commandServices.RecipeCommandService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.queryServices.RecipeQueryService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetAllRecipesQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetRecipeByIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {
    private final RecipeQueryService recipeQueryService;
    private final RecipeCommandService recipeCommandService;

    public RecipeController(RecipeQueryService recipeQueryService, RecipeCommandService recipeCommandService) {
        this.recipeQueryService = recipeQueryService;
        this.recipeCommandService = recipeCommandService;
    }

    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody CreateRecipeResource resource){
        System.out.println("📥 Resource recibido: " + resource);
        var createRecipeCommand = CreateRecipeCommandFromResourceAssembler.toCommandFromResource(resource);
        var recipe = recipeCommandService.handle(createRecipeCommand);

        if (recipe.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una receta con esas características.");
        }

        var resourceResponse = RecipeResourceFromEntityAssembler.toResourceFromEntity(recipe.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(resourceResponse);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Long id,@RequestBody UpdateRecipeResource resource) {
        var updateRecipeCommand = UpdateRecipeCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var recipe = recipeCommandService.handle(updateRecipeCommand);

        if (recipe.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe no encontrado.");
        }

        var resourceResponse = RecipeResourceFromEntityAssembler.toResourceFromEntity(recipe.get());
        return ResponseEntity.ok(resourceResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        var command = new DeleteRecipeCommand(id);
        recipeCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<RecipeResource>> getAllRecipes() {
        var recipe = recipeQueryService.handle(new GetAllRecipesQuery());
        var resources = recipe.stream()
                .map(RecipeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResource> getRecipeById(@PathVariable Long id) {
        var getRecipeByIdQuery = new GetRecipeByIdQuery(id);
        var recipe = recipeQueryService.handle(getRecipeByIdQuery);

        if (recipe.isEmpty())
            return ResponseEntity.badRequest().build();
        var recipeResource = RecipeResourceFromEntityAssembler.toResourceFromEntity(recipe.get());
        return ResponseEntity.ok(recipeResource);
    }


}
