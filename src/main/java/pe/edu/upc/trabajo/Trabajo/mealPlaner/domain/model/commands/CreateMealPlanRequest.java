package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Day;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;

import java.util.List;

public record CreateMealPlanRequest(
        String name,
        String category,
        String description,
        Goal goal,
        Float min_bmi,
        Float max_bmi,
        Long min_age,
        Long max_age,
        Long calories_per_day,
        List<RecipeDayDTO> recipesByDay
) {
    public record RecipeDayDTO(
            String day,
            List<MealDTO> meals
    ) {}

    public record MealDTO(
            String meal_time,
            Long recipe_id
    ) {}
}
