package pe.edu.upc.trabajo.Trabajo.mealPlanner.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.commands.CreateMealPlanRequest;
import pe.edu.upc.trabajo.Trabajo.mealPlaner.domain.model.typeObject.Goal;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class MealPlanRecipeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Rollback
    void shouldCreateMealPlanAndReturn201() throws Exception {
        // Arrange: construir una solicitud con receta_id existente
        CreateMealPlanRequest.MealDTO mealDTO = new CreateMealPlanRequest.MealDTO("almuerzo", 1L);
        CreateMealPlanRequest.RecipeDayDTO recipeDayDTO = new CreateMealPlanRequest.RecipeDayDTO("lunes", List.of(mealDTO));
        CreateMealPlanRequest request = new CreateMealPlanRequest(
                "Plan semanal",
                "Definición",
                "Plan para 7 días",
                Goal.Mantenimiento,
                18.5f,
                24.9f,
                18L,
                40L,
                2000L,
                List.of(recipeDayDTO)
        );

        // Act + Assert
        mockMvc.perform(post("/mealPlanRecipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Plan semanal"))
                .andExpect(jsonPath("$.category").value("Definición"))
                .andExpect(jsonPath("$.description").value("Plan para 7 días"));
    }
}
