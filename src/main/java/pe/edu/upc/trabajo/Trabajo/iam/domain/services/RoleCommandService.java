package pe.edu.upc.trabajo.Trabajo.iam.domain.services;

import pe.edu.upc.trabajo.Trabajo.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
