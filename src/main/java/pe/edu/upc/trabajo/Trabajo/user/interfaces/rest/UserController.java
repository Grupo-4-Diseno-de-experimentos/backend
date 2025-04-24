package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest;

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

import java.util.List;
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

    @PostMapping(value = "/login")
    public ResponseEntity<Optional<User>> login(@RequestBody LoginCommand loginCommand){
        var getUserQuery = new GetUserByEmailAndPasswordQuery(loginCommand.email(), loginCommand.password());
        var user = userQueryService.handle(getUserQuery);
        if (user == null) { return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var usersResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();

        return ResponseEntity.ok(usersResources);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserResource createUserResource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var user = userCommandService.handle(createUserCommand);
        return user.map(u -> new ResponseEntity<>(u, HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var deleteUserByIdCommand = new DeleteUserCommand(id);
        userCommandService.handle(deleteUserByIdCommand);
        return ResponseEntity.ok().build();
    }

}
