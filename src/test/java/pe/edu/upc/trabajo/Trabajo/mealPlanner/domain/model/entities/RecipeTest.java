package pe.edu.upc.trabajo.Trabajo.mealPlanner.domain.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.Recipe;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Macros;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.aggregate.Ingredient;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateRecipeCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    private Ingredient arroz;
    private Ingredient pollo;
    private List<Ingredient> ingredientes;

    @BeforeEach
    void setUp() {
        arroz = new Ingredient("Arroz", 100f, 130f, 28f, 2.7f, 0.3f, "Cereal", true);
        pollo = new Ingredient("Pollo", 100f, 165f, 0f, 31f, 3.6f, "Proteína", true);
        ingredientes = List.of(arroz, pollo);
    }

    @Test
    void shouldCreateRecipeWithConstructor() {
        Macros macros = new Macros(50.0, 30.0, 10.0);
        Recipe recipe = new Recipe(1L, "Pollo con arroz", "Receta tradicional", "Cocinar y servir", 500L, macros, ingredientes);

        assertEquals("Pollo con arroz", recipe.getTitle());
        assertEquals("Receta tradicional", recipe.getDescription());
        assertEquals("Cocinar y servir", recipe.getInstructions());
        assertEquals(500L, recipe.getCalories());

        assertEquals(50.0, recipe.getMacros().getCarbs(), 0.001);
        assertEquals(30.0, recipe.getMacros().getProtein(), 0.001);
        assertEquals(10.0, recipe.getMacros().getFats(), 0.001);

        assertEquals(2, recipe.getIngredients().size());
        assertEquals("Arroz", recipe.getIngredients().get(0).getName());
        assertEquals("Pollo", recipe.getIngredients().get(1).getName());
    }

    @Test
    void shouldCreateRecipeFromCreateRecipeCommand() {
        CreateRecipeCommand command = new CreateRecipeCommand(
                "Ensalada fresca",
                "Receta ligera y rápida",
                "Picar, mezclar y servir",
                280L,
                12.5,
                5.0,
                18.0,
                List.of(1L, 2L)
        );

        Recipe recipe = new Recipe(command, ingredientes);

        assertEquals("Ensalada fresca", recipe.getTitle());
        assertEquals("Receta ligera y rápida", recipe.getDescription());
        assertEquals("Picar, mezclar y servir", recipe.getInstructions());
        assertEquals(280L, recipe.getCalories());

        assertEquals(12.5, recipe.getMacros().getCarbs(), 0.001);
        assertEquals(5.0, recipe.getMacros().getProtein(), 0.001);
        assertEquals(18.0, recipe.getMacros().getFats(), 0.001);

        assertEquals(2, recipe.getIngredients().size());
        assertEquals("Arroz", recipe.getIngredients().get(0).getName());
        assertEquals("Pollo", recipe.getIngredients().get(1).getName());
    }

    @Test
    void shouldUpdateRecipeFromUpdateRecipeCommand() {
        Recipe recipe = new Recipe(
                1L,
                "Antiguo título",
                "Antigua descripción",
                "Antiguas instrucciones",
                600L,
                new Macros(60.0, 20.0, 10.0),
                ingredientes
        );

        Ingredient espinaca = new Ingredient("Espinaca", 50f, 23f, 3.6f, 2.9f, 0.4f, "Verdura", true);
        List<Ingredient> nuevosIngredientes = List.of(espinaca);

        UpdateRecipeCommand command = new UpdateRecipeCommand(
                1L,
                "Batido verde",
                "Desintoxicante",
                "Licuar y servir frío",
                250L,
                20.0,
                10.0,
                5.0,
                List.of(3L)
        );

        recipe.updateRecipeCommand(command, nuevosIngredientes);

        assertEquals("Batido verde", recipe.getTitle());
        assertEquals("Desintoxicante", recipe.getDescription());
        assertEquals("Licuar y servir frío", recipe.getInstructions());
        assertEquals(250L, recipe.getCalories());

        assertEquals(20.0, recipe.getMacros().getCarbs(), 0.001);
        assertEquals(10.0, recipe.getMacros().getProtein(), 0.001);
        assertEquals(5.0, recipe.getMacros().getFats(), 0.001);

        assertEquals(1, recipe.getIngredients().size());
        assertEquals("Espinaca", recipe.getIngredients().get(0).getName());
    }
}
