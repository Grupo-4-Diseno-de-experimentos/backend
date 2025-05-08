package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float quantity;
    private Float calories;
    private Float carbs;
    private Float protein;
    private Float fats;
    private String category;
    private Boolean available;
}
