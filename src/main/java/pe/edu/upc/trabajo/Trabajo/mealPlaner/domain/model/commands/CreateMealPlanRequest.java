package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

import java.util.List;

public record CreateMealPlanRequest(
        String name,
        String category,
        String goal,
        String description,
        Double minBmi,
        Double maxBmi,
        Integer minAge,
        Integer maxAge,
        Integer caloriesPerDay,
        List<DayMealGroup> recipesByDay
) {
    public record DayMealGroup(
            String day,
            List<MealEntry> meals
    ) {}

    public record MealEntry(
            String mealTime,
            Long recipeId
    ) {}
}
