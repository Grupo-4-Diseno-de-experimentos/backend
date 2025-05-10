package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;

public record CreateMealPlanCommand(String name,
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
