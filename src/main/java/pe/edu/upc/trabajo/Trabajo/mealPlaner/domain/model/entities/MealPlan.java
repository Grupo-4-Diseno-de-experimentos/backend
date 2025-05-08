package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;

@Data
@Entity
@NoArgsConstructor
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
    private String goal;
    private Float min_bmi;
    private Float max_bmi;
    private Long min_age;
    private Long max_age;
    private Long calories_per_d;

    @ManyToOne
    @JoinColumn(name = "nutricionist_id")
    private Nutricionist nutricionist;



}
