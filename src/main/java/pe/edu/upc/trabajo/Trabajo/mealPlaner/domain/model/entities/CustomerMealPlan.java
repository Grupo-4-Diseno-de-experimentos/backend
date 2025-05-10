package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import jakarta.persistence.*;
import pe.edu.upc.trabajo.Trabajo.profile.domain.model.entities.Customer;

@Entity
@Table(name = "customer_meal_plans")
public class CustomerMealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean is_current;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;
}
