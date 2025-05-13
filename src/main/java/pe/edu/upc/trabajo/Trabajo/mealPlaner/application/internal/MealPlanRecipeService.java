package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRequest;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Day;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Mealtime;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRecipeRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;

import java.time.DayOfWeek;

@Service
public class MealPlanRecipeService {

    private final IMealPlanRecipeRepository mealPlanRecipeRepository;
    private final IRecipeRepository recipeRepository;
    private final IMealPlanRepository mealPlanRepository;

    private Mealtime mapMealtime(String mealTime) {
        return switch (mealTime.toLowerCase()) {
            case "desayuno", "breakfast" -> Mealtime.Desayuno;
            case "almuerzo", "lunch" -> Mealtime.Almuerzo;
            case "cena", "dinner" -> Mealtime.Cena;
            default -> throw new IllegalArgumentException("Mealtime inválido: " + mealTime);
        };
    }

    private Day mapDay(String day) {
        return switch (day.toLowerCase()) {
            case "monday", "lunes" -> Day.Lunes;
            case "tuesday", "martes" -> Day.Martes;
            case "wednesday", "miércoles", "miercoles" -> Day.Miercoles;
            case "thursday", "jueves" -> Day.Jueves;
            case "friday", "viernes" -> Day.Viernes;
            case "saturday", "sábado", "sabado" -> Day.Sabado;
            case "sunday", "domingo" -> Day.Domingo;
            default -> throw new IllegalArgumentException("Día inválido: " + day);
        };
    }

    @Autowired
    public MealPlanRecipeService(IMealPlanRecipeRepository mealPlanRecipeRepository,
                                 IRecipeRepository recipeRepository,
                                 IMealPlanRepository mealPlanRepository) {
        this.mealPlanRecipeRepository = mealPlanRecipeRepository;
        this.recipeRepository = recipeRepository;
        this.mealPlanRepository = mealPlanRepository;
    }

    @Transactional
    public MealPlan createMealPlanWithRecipes(CreateMealPlanRequest request) {

        MealPlan mealPlan = new MealPlan();
        mealPlan.setName(request.name());
        mealPlan.setCategory(request.category());
        mealPlan.setDescription(request.description());
        mealPlan.setGoal(request.goal());
        mealPlan.setMin_bmi(request.min_bmi());
        mealPlan.setMax_bmi(request.max_bmi());
        mealPlan.setMin_age(request.min_age());
        mealPlan.setMax_age(request.max_age());
        mealPlan.setCalories_per_d(request.calories_per_day());

        MealPlan savedPlan = mealPlanRepository.save(mealPlan);

        for (CreateMealPlanRequest.RecipeDayDTO dayDTO : request.recipesByDay()) {
            Day day = mapDay(dayDTO.day());

            for (CreateMealPlanRequest.MealDTO mealDTO : dayDTO.meals()) {
                Mealtime mealtime = mapMealtime(mealDTO.meal_time());

                Recipe recipe = recipeRepository.findById(mealDTO.recipe_id())
                        .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + mealDTO.recipe_id()));

                MealPlanRecipe mealPlanRecipe = new MealPlanRecipe();
                mealPlanRecipe.setMealPlan(savedPlan);
                mealPlanRecipe.setDay(day);
                mealPlanRecipe.setMealTime(mealtime);
                mealPlanRecipe.setRecipe(recipe);

                mealPlanRecipeRepository.save(mealPlanRecipe);
            }
        }

        return savedPlan;
    }
}

