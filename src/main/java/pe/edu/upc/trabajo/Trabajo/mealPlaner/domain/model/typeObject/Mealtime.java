package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Mealtime {
    Desayuno,
    Almuerzo,
    Cena;
    @JsonCreator
    public static Mealtime fromString(String value) {
        switch (value.toLowerCase()) {
            case "breakfast":
                return Desayuno;
            case "lunch":
                return Almuerzo;
            case "dinner":
                return Cena;
            default:
                throw new IllegalArgumentException("Unknown mealtime: " + value);
        }
    }
}
