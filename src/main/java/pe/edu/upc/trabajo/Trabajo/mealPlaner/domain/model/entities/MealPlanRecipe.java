package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "meal_plan_recipes")
public class MealPlanRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;
    private String mealTime;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    public MealPlanRecipe(){

    }
    public MealPlanRecipe(Long id, String day, String mealTime, Recipe recipe, MealPlan mealPlan) {
        this.id = id;
        this.day = day;
        this.mealTime = mealTime;
        this.recipe = recipe;
        this.mealPlan = mealPlan;
    }


}
