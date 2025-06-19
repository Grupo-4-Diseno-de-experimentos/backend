package pe.edu.upc.trabajo.Trabajo.mealPlanner.domain.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.entities.MealPlan;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.UpdateMealPlanCommand;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;

import static org.junit.jupiter.api.Assertions.*;

class MealPlanTest {

    private MealPlan mealPlan;

    @BeforeEach
    void setUp() {
        mealPlan = new MealPlan(
                1L,
                "Plan de definición",
                "Definición",
                "Plan para pérdida de grasa",
                Goal.PerdidaDePeso,
                18.5f,
                24.9f,
                18L,
                35L,
                2000L
        );
    }

    @Test
    void shouldCreateMealPlanWithCorrectAttributes() {
        assertEquals("Plan de definición", mealPlan.getName());
        assertEquals("Definición", mealPlan.getCategory());
        assertEquals("Plan para pérdida de grasa", mealPlan.getDescription());
        assertEquals(Goal.PerdidaDePeso, mealPlan.getGoal());
        assertEquals(18.5f, mealPlan.getMin_bmi());
        assertEquals(24.9f, mealPlan.getMax_bmi());
        assertEquals(18L, mealPlan.getMin_age());
        assertEquals(35L, mealPlan.getMax_age());
        assertEquals(2000L, mealPlan.getCalories_per_d());
    }

    @Test
    void shouldUpdateMealPlanCorrectly() {
        UpdateMealPlanCommand command = new UpdateMealPlanCommand(
                1L, // mealPlanId (no se usa en update pero es requerido por el record)
                "Plan volumen",
                "Volumen",
                "Plan para ganar masa muscular",
                Goal.GanarMasaMuscular,
                20.0f,
                28.0f,
                21L,
                40L,
                2800L
        );

        mealPlan.updateMealPlanCommand(command);

        assertEquals("Plan volumen", mealPlan.getName());
        assertEquals("Volumen", mealPlan.getCategory());
        assertEquals("Plan para ganar masa muscular", mealPlan.getDescription());
        assertEquals(Goal.GanarMasaMuscular, mealPlan.getGoal());
        assertEquals(20.0f, mealPlan.getMin_bmi());
        assertEquals(28.0f, mealPlan.getMax_bmi());
        assertEquals(21L, mealPlan.getMin_age());
        assertEquals(40L, mealPlan.getMax_age());
        assertEquals(2800L, mealPlan.getCalories_per_d());
    }
}
