package pe.edu.upc.trabajo.Trabajo.user.domain.model.commands;

public record UpdateNutricionistCommand(Long nutricionistId,String name, String lastName, String code) {
}
