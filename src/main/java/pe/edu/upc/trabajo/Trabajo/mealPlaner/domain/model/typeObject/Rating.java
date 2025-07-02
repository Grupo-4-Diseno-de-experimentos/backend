package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject;

import jakarta.persistence.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating; // 1 a 5

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
