package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;

public record MealPlanerResource(Long id,
                                 String name,
                                 String category,
                                 String description,
                                 Goal goal,
                                 Float min_bmi,
                                 Float max_bmi,
                                 Long min_age,
                                 Long max_age,
                                 Long calories_per_d,
                                 Long nutricionistId) {
}
