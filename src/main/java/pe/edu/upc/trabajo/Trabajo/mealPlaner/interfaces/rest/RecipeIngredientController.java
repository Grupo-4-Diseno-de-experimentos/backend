package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.RecipeIngredientService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.RecipeIngredientResponse;

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
}
