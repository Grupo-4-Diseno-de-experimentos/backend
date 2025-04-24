package pe.edu.upc.trabajo.Trabajo.user.domain.model.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginCommand(
        @NotBlank(message = "Correo obligatorio")
        @Email(message = "Correo invalido")
        String email,
        @NotBlank(message = "Contraseña obligatoria")
        String password) {
}
