package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.commandServices.MealPlanCommandService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.queryServices.MealPlanQueryService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.DeleteMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetAllMealPlansQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetMealPlanByIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetMealPlanRecipesByPlanIdQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.CreateMealPlanerResource;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.MealPlanerResource;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.UpdateMealPlanerResource;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers.CreateMealPlanerCommandFromResourceAssembler;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers.MealPlanerResourceFromEntityAssembler;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers.UpdateMealPlanerCommandFromResourceAssembler;

import java.util.List;

@RestController
@RequestMapping(value = "/mealPlaner")
public class MealPlanController {
    private final MealPlanQueryService mealPlanQueryService;
    private final MealPlanCommandService mealPlanCommandService;

    public MealPlanController(MealPlanQueryService mealPlanQueryService, MealPlanCommandService mealPlanCommandService) {
        this.mealPlanQueryService = mealPlanQueryService;
        this.mealPlanCommandService = mealPlanCommandService;
    }

    @PostMapping
    public ResponseEntity<?> createMealPlan(@RequestBody CreateMealPlanerResource resource){
        var createMealPlanCommand = CreateMealPlanerCommandFromResourceAssembler.toCommandFromResource(resource);
        var mealPlan = mealPlanCommandService.handle(createMealPlanCommand);

        if (mealPlan.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un plan con esas características.");
        }

        var resourceResponse = MealPlanerResourceFromEntityAssembler.toResourceFromEntity(mealPlan.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(resourceResponse);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMealPlan(@PathVariable Long id,@RequestBody UpdateMealPlanerResource resource) {
        var updateMealPlanCommand = UpdateMealPlanerCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var mealPlan = mealPlanCommandService.handle(updateMealPlanCommand);

        if (mealPlan.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meal plan no encontrado.");
        }

        var resourceResponse = MealPlanerResourceFromEntityAssembler.toResourceFromEntity(mealPlan.get());
        return ResponseEntity.ok(resourceResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMealPlan(@PathVariable Long id) {
        var command = new DeleteMealPlanCommand(id);
        mealPlanCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MealPlanerResource>> getAllMealPlans() {
        var mealPlans = mealPlanQueryService.handle(new GetAllMealPlansQuery());
        var resources = mealPlans.stream()
                .map(MealPlanerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlanerResource> getMealPlanById(@PathVariable Long id) {
        var getMealPlanByIdQuery = new GetMealPlanByIdQuery(id);
        var mealPlan = mealPlanQueryService.handle(getMealPlanByIdQuery);

        if (mealPlan.isEmpty())
            return ResponseEntity.badRequest().build();
        var mealPlanResource = MealPlanerResourceFromEntityAssembler.toResourceFromEntity(mealPlan.get());
        return ResponseEntity.ok(mealPlanResource);
    }

    @GetMapping("/{id}/recipes")
    public ResponseEntity<?> getMealPlanWithRecipes(@PathVariable Long id) {
        var getMealPlanByIdQuery = new GetMealPlanRecipesByPlanIdQuery(id);
        var mealPlan = mealPlanQueryService.handle(getMealPlanByIdQuery);

        if (mealPlan.isEmpty())
            return ResponseEntity.badRequest().build();
        var mealPlanResource = MealPlanerResourceFromEntityAssembler.toResourceFromEntity(mealPlan.get());
        return ResponseEntity.ok(mealPlanResource);
    }
}
