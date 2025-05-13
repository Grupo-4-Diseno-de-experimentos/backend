package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.MealPlanRecipeService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;

@RestController
@RequestMapping("/mealPlanRecipes")
public class MealPlanRecipeController {

    private final MealPlanRecipeService mealPlanRecipeService;

    @Autowired
    public MealPlanRecipeController(MealPlanRecipeService mealPlanRecipeService) {
        this.mealPlanRecipeService = mealPlanRecipeService;
    }

    @PostMapping
    public ResponseEntity<MealPlanRecipe> createMealPlanRecipe(@RequestBody CreateMealPlanRecipeCommand command) {
        MealPlanRecipe mealPlanRecipe = mealPlanRecipeService.createMealPlanRecipe(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(mealPlanRecipe);
    }

}
