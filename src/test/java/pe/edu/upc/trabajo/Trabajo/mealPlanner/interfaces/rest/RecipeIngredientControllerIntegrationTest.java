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
import pe.edu.upc.trabajo.Trabajo.mealPlaner.interfaces.rest.resources.CreateRecipeIngredientResource;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class RecipeIngredientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Rollback
    void shouldCreateRecipeIngredientAndReturn201() throws Exception {
        // Asegúrate de que existan estos IDs en tu base de datos de prueba
        CreateRecipeIngredientResource resource = new CreateRecipeIngredientResource(
                1L, // receta existente
                2L, // ingrediente existente
                100.0
        );

        String jsonRequest = objectMapper.writeValueAsString(List.of(resource));

        mockMvc.perform(post("/recipe_ingredients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].recipe_id").value(1))
                .andExpect(jsonPath("$[0].ingredient_id").value(2))
                .andExpect(jsonPath("$[0].quantity").value(100.0));
    }
}
