package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    public RecipeController(RecipeQueryService recipeQueryService) {
        this.recipeQueryService = recipeQueryService;
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
