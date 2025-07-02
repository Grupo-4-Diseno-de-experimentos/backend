package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;

import java.util.List;

@Data
@Entity
@Table(name = "mealPlan")
@Getter
@Setter
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String description;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    private Float min_bmi;
    private Float max_bmi;
    private Long min_age;
    private Long max_age;
    private Long calories_per_d;

    @ManyToOne
    @JoinColumn(name = "nutricionist_id")
    private Nutricionist nutricionist;

    @OneToMany(mappedBy = "mealPlan")
    @JsonIgnore
    private List<CustomerMealPlan> customerMealPlans;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MealPlanRecipe> mealPlanRecipes;

    public MealPlan(){
    }
    public MealPlan(Long id, String name, String category, String description, Goal goal, Float min_bmi, Float max_bmi, Long min_age, Long max_age, Long calories_per_d) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.goal = goal;
        this.min_bmi = min_bmi;
        this.max_bmi = max_bmi;
        this.min_age = min_age;
        this.max_age = max_age;
        this.calories_per_d = calories_per_d;
    }

    public MealPlan(CreateMealPlanCommand command){
        this.name = command.name();
        this.category = command.category();
        this.description = command.description();
        this.goal = command.goal();
        this.min_bmi = command.min_bmi();
        this.max_bmi = command.max_bmi();
        this.min_age = command.min_age();
        this.max_age = command.max_age();
        this.calories_per_d = command.calories_per_d();
    }

    public void updateMealPlanCommand(UpdateMealPlanCommand command){
        this.name = command.name();
        this.category = command.category();
        this.description = command.description();
        this.goal = command.goal();
        this.min_bmi = command.min_bmi();
        this.max_bmi = command.max_bmi();
        this.min_age = command.min_age();
        this.max_age = command.max_age();
        this.calories_per_d = command.calories_per_d();
    }

    public MealPlan(Long id){
        this.id = id;
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Goal getGoal() {
        return goal;
    }

    public Float getMin_bmi() {
        return min_bmi;
    }

    public Float getMax_bmi() {
        return max_bmi;
    }

    public Long getMin_age() {
        return min_age;
    }

    public Long getMax_age() {
        return max_age;
    }

    public Long getCalories_per_d() {
        return calories_per_d;
    }

    public Nutricionist getNutricionist() {
        return nutricionist;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setMin_bmi(Float min_bmi) {
        this.min_bmi = min_bmi;
    }

    public void setMax_bmi(Float max_bmi) {
        this.max_bmi = max_bmi;
    }

    public void setMin_age(Long min_age) {
        this.min_age = min_age;
    }

    public void setMax_age(Long max_age) {
        this.max_age = max_age;
    }

    public void setCalories_per_d(Long calories_per_d) {
        this.calories_per_d = calories_per_d;
    }

    public void setNutricionist(Nutricionist nutricionist) {
        this.nutricionist = nutricionist;
    }
}
