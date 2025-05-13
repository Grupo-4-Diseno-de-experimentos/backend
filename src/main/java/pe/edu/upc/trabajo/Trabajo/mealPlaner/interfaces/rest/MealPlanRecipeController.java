package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.MealPlanRecipeService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRequest;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetMealPlanRecipesByPlanIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.services.IMealPlanRecipeQueryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mealPlanRecipes")
public class MealPlanRecipeController {

    private final MealPlanRecipeService mealPlanRecipeService;
    private final IMealPlanRecipeQueryService mealPlanRecipeQueryService;

    @Autowired
    public MealPlanRecipeController(MealPlanRecipeService mealPlanRecipeService, IMealPlanRecipeQueryService mealPlanRecipeQueryService) {
        this.mealPlanRecipeService = mealPlanRecipeService;
        this.mealPlanRecipeQueryService = mealPlanRecipeQueryService;
    }

    @PostMapping
    public ResponseEntity<MealPlan> createPlan(@RequestBody CreateMealPlanRequest request) {
        MealPlan created = mealPlanRecipeService.createMealPlanWithRecipes(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MealPlanRecipe>> getMealPlanRecipesByPlanId(@PathVariable Long id) {
        GetMealPlanRecipesByPlanIdQuery query = new GetMealPlanRecipesByPlanIdQuery(id);
        Optional<List<MealPlanRecipe>> mealPlanRecipes = mealPlanRecipeQueryService.handle(query);

        if (mealPlanRecipes.isPresent() && !mealPlanRecipes.get().isEmpty()) {
            return ResponseEntity.ok(mealPlanRecipes.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
