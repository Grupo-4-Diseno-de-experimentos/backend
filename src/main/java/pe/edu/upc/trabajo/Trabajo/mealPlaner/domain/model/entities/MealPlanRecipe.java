package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import jakarta.persistence.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Day;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.MealTime;

@Entity
@Table(name = "meal_plan_recipes")
public class MealPlanRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Day day;
    @Enumerated(EnumType.STRING)
    private MealTime mealTime;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    public MealPlanRecipe(){

    }

    public MealPlanRecipe(Long id, Day day, MealTime mealTime, Recipe recipe, MealPlan mealPlan) {
        this.id = id;
        this.day = day;
        this.mealTime = mealTime;
        this.recipe = recipe;
        this.mealPlan = mealPlan;
    }
}
