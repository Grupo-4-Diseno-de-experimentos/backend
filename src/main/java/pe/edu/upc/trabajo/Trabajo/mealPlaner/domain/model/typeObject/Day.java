package pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Day {
    Lunes,
    Martes,
    Miercoles,
    Jueves,
    Viernes,
    Sabado,
    Domingo;
    @JsonCreator
    public static Day fromString(String value) {
        switch (value.toLowerCase()) {
            case "monday":
                return Lunes;
            case "tuesday":
                return Martes;
            case "wednesday":
                return Miercoles;
            case "thursday":
                return Jueves;
            case "friday":
                return Viernes;
            case "saturday":
                return Sabado;
            case "sunday":
                return Domingo;
            default:
                throw new IllegalArgumentException("Unknown day: " + value);
        }
    }
}
