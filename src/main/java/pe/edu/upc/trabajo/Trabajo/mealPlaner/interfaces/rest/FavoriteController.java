package pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest;

import jakarta.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.commandServices.FavoriteRecipeCommandService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.queryServices.FavoriteRecipeQueryService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.FavoriteRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.AddRecipeToFavoriteCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetFavoritesByUserQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.AddRecipeToFavoriteResource;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.FavoriteRecipeResource;

import java.util.List;

@RestController
@RequestMapping(value = "/favorite")
public class FavoriteController {
    private final FavoriteRecipeCommandService commandService;
    private final FavoriteRecipeQueryService queryService;

    private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);

    public FavoriteController(FavoriteRecipeCommandService commandService, FavoriteRecipeQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestBody AddRecipeToFavoriteResource resource) {
        var command = new AddRecipeToFavoriteCommand(resource.userId(), resource.recipeId());
        var favorite = commandService.handle(command);

        var response = new FavoriteRecipeResource(
                favorite.getId(),
                favorite.getUser().getId(),
                favorite.getRecipe().getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getFavoritesByUser(@PathVariable Long userId) {
        List<FavoriteRecipe> favorites = queryService.handle(new GetFavoritesByUserQuery(userId));

        List<FavoriteRecipeResource> response = favorites.stream().map(fav ->
                new FavoriteRecipeResource(
                        fav.getId(),
                        fav.getUser().getId(),
                        fav.getRecipe().getId()
                )
        ).toList();

        return ResponseEntity.ok(response);
    }

}
