package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate;

import jakarta.persistence.*;

@Embeddable
public class Macros {
    private Double carbs;
    private Double protein;
    private Double fats;

    public Macros() {}

    public Macros(Double carbs, Double protein, Double fats) {
        this.carbs = carbs;
        this.protein = protein;
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }
}
