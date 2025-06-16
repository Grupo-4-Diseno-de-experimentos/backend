package pe.edu.upc.trabajo.Trabajo.mealPlanner.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.RecipeIngredientService;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeIngredientCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeIngredientCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.RecipeIngredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeIngredientRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IIngredientRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeIngredientServiceTest {

    @Mock
    private IRecipeIngredientRepository recipeIngredientRepository;

    @Mock
    private IRecipeRepository recipeRepository;

    @Mock
    private IIngredientRepository ingredientRepository;

    @InjectMocks
    private RecipeIngredientService service;

    private Recipe recipe;
    private Ingredient ingredient;

    @BeforeEach
    void setup() {
        recipe = new Recipe();
        recipe.setId(1L);

        ingredient = new Ingredient();
        ingredient.setId(2L);
    }

    @Test
    void shouldCreateRecipeIngredientSuccessfully() {
        CreateRecipeIngredientCommand command = new CreateRecipeIngredientCommand(1L, 2L, 100.0);

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
        when(recipeIngredientRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        RecipeIngredient result = service.create(command);

        assertEquals(recipe, result.getRecipe());
        assertEquals(ingredient, result.getIngredient());
        assertEquals(100.0, result.getQuantity());
    }

    @Test
    void shouldThrowIfRecipeNotFoundOnCreate() {
        CreateRecipeIngredientCommand command = new CreateRecipeIngredientCommand(99L, 2L, 100.0);
        when(recipeRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            service.create(command);
        });

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        assertTrue(ex.getReason().contains("Recipe not found"));
    }

    @Test
    void shouldUpdateRecipeIngredientSuccessfully() {
        RecipeIngredient existing = new RecipeIngredient();
        existing.setId(10L);
        existing.setRecipe(recipe);
        existing.setIngredient(ingredient);
        existing.setQuantity(50.0);

        UpdateRecipeIngredientCommand command = new UpdateRecipeIngredientCommand(10L, 1L, 2L, 120.0);

        when(recipeIngredientRepository.findById(10L)).thenReturn(Optional.of(existing));
        lenient().when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        lenient().when(ingredientRepository.findById(2L)).thenReturn(Optional.of(ingredient));
        when(recipeIngredientRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        RecipeIngredient result = service.update(command);

        assertEquals(120.0, result.getQuantity());
        assertEquals(recipe, result.getRecipe());
        assertEquals(ingredient, result.getIngredient());
    }

    @Test
    void shouldThrowIfRecipeIngredientNotFoundOnUpdate() {
        UpdateRecipeIngredientCommand command = new UpdateRecipeIngredientCommand(99L, 1L, 2L, 120.0);
        when(recipeIngredientRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            service.update(command);
        });

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        assertTrue(ex.getReason().contains("RecipeIngredient not found"));
    }

    @Test
    void shouldGetRecipeIngredientsByRecipeId() {
        RecipeIngredient ri = new RecipeIngredient();
        when(recipeIngredientRepository.findByRecipeId(1L)).thenReturn(List.of(ri));

        List<RecipeIngredient> result = service.getRecipeIngredientsByRecipeId(1L);

        assertEquals(1, result.size());
    }

    @Test
    void shouldDeleteRecipeIngredientSuccessfully() {
        when(recipeIngredientRepository.existsById(10L)).thenReturn(true);

        service.delete(10L);

        verify(recipeIngredientRepository).deleteById(10L);
    }

    @Test
    void shouldThrowIfRecipeIngredientNotFoundOnDelete() {
        when(recipeIngredientRepository.existsById(99L)).thenReturn(false);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            service.delete(99L);
        });

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}
