package pe.edu.upc.trabajo.Trabajo.user.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.commandServices.NutricionistCommandService;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.queryServices.NutricionistQueryService;
import pe.edu.upc.trabajo.Trabajo.user.application.internal.queryServices.UserQueryService;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.DeleteNutricionistCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.LoginCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.commands.LoginNutCommand;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.Nutricionist;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.entities.User;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetAllNutricionistsQuery;
import pe.edu.upc.trabajo.Trabajo.user.domain.model.queries.GetUserByEmailAndPasswordQuery;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.resources.CreateNutricionistResource;
import pe.edu.upc.trabajo.Trabajo.user.interfaces.rest.transformers.CreateNutricionistCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/nutricionist")
public class NutricionistController {
    private final NutricionistQueryService nutricionistQueryService;
    private final NutricionistCommandService nutricionistCommandService;

    public NutricionistController(NutricionistQueryService nutricionistQueryService, NutricionistCommandService nutricionistCommandService) {
        this.nutricionistQueryService = nutricionistQueryService;
        this.nutricionistCommandService = nutricionistCommandService;
    }

    @GetMapping
    public ResponseEntity<List<Nutricionist>> getallNutricionists(){
        var getAllNutricionistsQuery = new GetAllNutricionistsQuery();
        var nutricionists = nutricionistQueryService.handle(getAllNutricionistsQuery);
        return new ResponseEntity<>(nutricionists, HttpStatus.OK);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<Nutricionist> login(@RequestBody LoginNutCommand loginNutCommand){
        Optional<Nutricionist> nutricionist = nutricionistQueryService.login(loginNutCommand.Code());
        return nutricionist
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    @PostMapping
    public ResponseEntity<Nutricionist> createNutricionist(@RequestBody CreateNutricionistResource createNutricionistResource) {
        var createNutricionistCommand = CreateNutricionistCommandFromResourceAssembler.toCommandFromResource(createNutricionistResource);
        var user = nutricionistCommandService.handle(createNutricionistCommand);
        return user.map(u -> new ResponseEntity<>(u, HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteNutricionist(@PathVariable Long id) {
        var deleteNutricionistCommand = new DeleteNutricionistCommand(id);
        nutricionistCommandService.handle(deleteNutricionistCommand);
        return ResponseEntity.ok().build();
    }

}
