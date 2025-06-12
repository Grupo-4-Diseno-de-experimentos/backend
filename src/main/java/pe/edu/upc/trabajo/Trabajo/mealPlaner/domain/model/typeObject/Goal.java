package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Goal {

    PerdidaDePeso("PerdidaDePeso"),
    GanarMasaMuscular("GanarMasaMuscular"),
    Mantenimiento("Mantenimiento");

    private final String value;

    Goal(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Goal fromString(String value) {
        if (value == null) return null;
        switch (value.toLowerCase()) {
            case "perdidadepeso":
            case "weight_loss":
                return PerdidaDePeso;
            case "ganarmasamuscular":
            case "muscle_gain":
                return GanarMasaMuscular;
            case "mantenimiento":
            case "maintenance":
                return Mantenimiento;
            default:
                throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
}
