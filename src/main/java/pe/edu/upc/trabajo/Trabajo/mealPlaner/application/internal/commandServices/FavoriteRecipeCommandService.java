package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.commandServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.FavoriteRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.AddRecipeToFavoriteCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IFavoriteRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;
import pe.edu.upc.trabajo.Trabajo.user.infrastructure.repositories.jpa.IUserRepository;

import java.util.Optional;

@Service
public class FavoriteRecipeCommandService {
    private final IFavoriteRepository favoriteRecipeRepository;
    private final IUserRepository userRepository;
    private final IRecipeRepository recipeRepository;

    public FavoriteRecipeCommandService(IFavoriteRepository favoriteRecipeRepository,
                                        IRecipeRepository recipeRepository,IUserRepository userRepository) {
        this.favoriteRecipeRepository = favoriteRecipeRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public FavoriteRecipe handle(AddRecipeToFavoriteCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var recipe = recipeRepository.findById(command.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        FavoriteRecipe favorite = new FavoriteRecipe();
        favorite.setUser(user);
        favorite.setRecipe(recipe);

        FavoriteRecipe savedFavorite = favoriteRecipeRepository.save(favorite);

        return savedFavorite;
    }
}
