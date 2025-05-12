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

    public Ingredient(){

    }

    public Ingredient(Long id, String name, Float quantity, Float calories, Float carbs, Float protein, Float fats, String category, Boolean available) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fats = fats;
        this.category = category;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public Float getCalories() {
        return calories;
    }

    public Float getCarbs() {
        return carbs;
    }

    public Float getProtein() {
        return protein;
    }

    public Float getFats() {
        return fats;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public void setCarbs(Float carbs) {
        this.carbs = carbs;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public void setFats(Float fats) {
        this.fats = fats;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
