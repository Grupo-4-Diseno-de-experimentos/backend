package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Day;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Mealtime;

@Entity
@Table(name = "meal_plan_recipes")
public class MealPlanRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Day day;
    @Enumerated(EnumType.STRING)
    private Mealtime mealTime;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    @JsonIgnore
    private MealPlan mealPlan;

    public MealPlanRecipe(){

    }

    public MealPlanRecipe(Long id, Day day, Mealtime mealTime, Recipe recipe, MealPlan mealPlan) {
        this.id = id;
        this.day = day;
        this.mealTime = mealTime;
        this.recipe = recipe;
        this.mealPlan = mealPlan;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Mealtime getMealTime() {
        return mealTime;
    }

    public void setMealTime(Mealtime mealTime) {
        this.mealTime = mealTime;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }
}
