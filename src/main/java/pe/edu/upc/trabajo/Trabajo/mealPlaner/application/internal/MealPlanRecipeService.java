package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Day;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.MealTime;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRecipeRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;

@Service
public class MealPlanRecipeService {

    private final IMealPlanRecipeRepository mealPlanRecipeRepository;
    private final IRecipeRepository recipeRepository;
    private final IMealPlanRepository mealPlanRepository;

    @Autowired
    public MealPlanRecipeService(IMealPlanRecipeRepository mealPlanRecipeRepository,
                                 IRecipeRepository recipeRepository,
                                 IMealPlanRepository mealPlanRepository) {
        this.mealPlanRecipeRepository = mealPlanRecipeRepository;
        this.recipeRepository = recipeRepository;
        this.mealPlanRepository = mealPlanRepository;
    }

    public MealPlanRecipe createMealPlanRecipe(CreateMealPlanRecipeCommand command) {

        Recipe recipe = recipeRepository.findById(command.recipeId())
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));

        MealPlan mealPlan = mealPlanRepository.findById(command.mealPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Meal Plan not found"));

        Day day = Day.valueOf(command.day());
        MealTime mealTime = MealTime.valueOf(command.mealTime());

        MealPlanRecipe mealPlanRecipe = new MealPlanRecipe(null, day, mealTime, recipe, mealPlan);

        return mealPlanRecipeRepository.save(mealPlanRecipe);
    }
}
