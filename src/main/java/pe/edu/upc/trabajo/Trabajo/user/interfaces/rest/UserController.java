package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.commandServices.UserCommandService;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.queryServices.UserQueryService;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.LoginCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetUserByEmailAndPasswordQuery;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.CreateUserResource;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.UserResource;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.transformers.CreateUserCommandFromResourceAssembler;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.transformers.UserResourceFromEntityAssembler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UserController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUserByEmailAndPassword(@Valid @RequestBody LoginCommand loginCommand) {
        var getUserQuery = new GetUserByEmailAndPasswordQuery(loginCommand.email(), loginCommand.password());
        var user = userQueryService.handle(getUserQuery);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Datos incorrectos");
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login exitoso");
        response.put("user", userResource);

        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);

        var usersResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromUser)
                .toList();

        return ResponseEntity.ok(usersResources);
    }
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserResource createUserResource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var user = userCommandService.handle(createUserCommand);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Usuario ya existente");
        }

        return user.map(u -> new ResponseEntity<>(u, HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var deleteUserByIdCommand = new DeleteUserCommand(id);
        userCommandService.handle(deleteUserByIdCommand);
        return ResponseEntity.ok().build();
    }

}
