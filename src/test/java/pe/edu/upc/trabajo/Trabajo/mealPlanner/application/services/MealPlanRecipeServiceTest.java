package pe.edu.upc.trabajo.Trabajo.mealPlanner.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRequest;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlanRecipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Day;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Mealtime;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRecipeRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IRecipeRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.infrastructure.repositories.jpa.IMealPlanRepository;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.application.internal.MealPlanRecipeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealPlanRecipeServiceTest {

    @Mock
    private IMealPlanRecipeRepository mealPlanRecipeRepository;

    @Mock
    private IRecipeRepository recipeRepository;

    @Mock
    private IMealPlanRepository mealPlanRepository;

    @InjectMocks
    private MealPlanRecipeService service;

    private Recipe fakeRecipe;

    @BeforeEach
    void setUp() {
        fakeRecipe = new Recipe();
        fakeRecipe.setId(1L);
        fakeRecipe.setTitle("Pollo al horno");
    }

    @Test
    void shouldCreateMealPlanWithRecipesSuccessfully() {
        // Arrange
        CreateMealPlanRequest.MealDTO mealDTO = new CreateMealPlanRequest.MealDTO("almuerzo", 1L);
        CreateMealPlanRequest.RecipeDayDTO recipeDayDTO = new CreateMealPlanRequest.RecipeDayDTO("lunes", List.of(mealDTO));

        CreateMealPlanRequest request = new CreateMealPlanRequest(
                "Plan semanal",
                "Definición",
                "Plan completo para la semana",
                Goal.Mantenimiento,
                18.5f,
                24.9f,
                20L,
                40L,
                2000L,
                List.of(recipeDayDTO)
        );

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(fakeRecipe));
        when(mealPlanRepository.save(any(MealPlan.class))).thenAnswer(inv -> {
            MealPlan plan = inv.getArgument(0);
            plan.setId(99L); // simula ID generado
            return plan;
        });
        when(mealPlanRecipeRepository.save(any(MealPlanRecipe.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        MealPlan result = service.createMealPlanWithRecipes(request);

        // Assert
        assertNotNull(result);
        assertEquals("Plan semanal", result.getName());
        assertEquals(Goal.Mantenimiento, result.getGoal());
        assertEquals(99L, result.getId());

        verify(recipeRepository, times(1)).findById(1L);
        verify(mealPlanRepository, times(1)).save(any(MealPlan.class));
        verify(mealPlanRecipeRepository, times(1)).save(any(MealPlanRecipe.class));
    }

    @Test
    void shouldThrowExceptionIfRecipeNotFound() {
        // Arrange
        CreateMealPlanRequest.MealDTO mealDTO = new CreateMealPlanRequest.MealDTO("almuerzo", 999L);
        CreateMealPlanRequest.RecipeDayDTO recipeDayDTO = new CreateMealPlanRequest.RecipeDayDTO("lunes", List.of(mealDTO));

        CreateMealPlanRequest request = new CreateMealPlanRequest(
                "Plan fallido",
                "Definición",
                "Plan sin receta válida",
                Goal.Mantenimiento,
                18.5f,
                24.9f,
                20L,
                40L,
                2000L,
                List.of(recipeDayDTO)
        );

        when(recipeRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.createMealPlanWithRecipes(request);
        });

        assertTrue(exception.getMessage().contains("Recipe not found with ID: 999"));
    }
}
