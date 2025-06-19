package pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.queryServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.FavoriteRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.queries.GetFavoritesByUserQuery;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IFavoriteRepository;

import java.util.List;

@Service
public class FavoriteRecipeQueryService {

    private final IFavoriteRepository favoriteRepository;

    public FavoriteRecipeQueryService(IFavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<FavoriteRecipe> handle(GetFavoritesByUserQuery query) {
        return favoriteRepository.findAllByUserId(query.userId());
    }

}
