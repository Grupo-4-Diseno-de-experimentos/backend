package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;

public record CreateMealPlanerResource(String name,
                                       String category,
                                       String description,
                                       String goal,
                                       Float min_bmi,
                                       Float max_bmi,
                                       Long min_age,
                                       Long max_age,
                                       Long calories_per_d) {
}
