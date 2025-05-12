package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Goal {

    PerdidaDePeso,
    GanarMasaMuscular,
    Mantenimiento;

    @JsonCreator
    public static Goal fromString(String value) {
        switch (value.toLowerCase()) {
            case "weight_loss":
                return PerdidaDePeso;
            case "maintenance":
                return Mantenimiento;
            case "muscle_gain":
                return GanarMasaMuscular;
            default:
                throw new IllegalArgumentException("Unknown value: " + value);
        }
    }

}
