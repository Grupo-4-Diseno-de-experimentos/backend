package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MealTime {
    Desayuno,
    Almuerzo,
    Cena;

    @JsonCreator
    public static MealTime fromString(String value) {
        switch (value.toLowerCase()) {
            case "breakfast":
                return Desayuno;
            case "lunch":
                return Almuerzo;
            case "dinner":
                return Cena;
            default:
                throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
}
