package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.transformers;

import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.MealPlanerResource;

public class MealPlanerResourceFromEntityAssembler {
    public static MealPlanerResource toResourceFromEntity(MealPlan entity){
        return new MealPlanerResource(entity.getId(), entity.getName(), entity.getCategory(), entity.getDescription(), entity.getGoal(), entity.getMin_bmi(), entity.getMax_bmi(), entity.getMin_age(), entity.getMax_age(), entity.getCalories_per_d());
    }
}
